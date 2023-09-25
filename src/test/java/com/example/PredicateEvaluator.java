package com.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class PredicateEvaluator {

	enum Operand {
		AND, OR, OPEN_BRACKET, OPEN_BRACKET_WITH_EXCLAMATION, CLOSE_BRACKET
	}

	enum Condition {
		EQUAL, NOT_EQUAL
	}

	private static final String NULL = "null";

	@FunctionalInterface
	public interface Evaluate {
		boolean apply(String key, String value, Condition condition);
	}
	
	
	private static class Evaluator {

		private Map<String, Integer> data = new HashMap<>();

		public Evaluator(Map<String, Integer> data) {
			this.data = data;
		}

		public boolean evaluate(String key, String value, Condition condition) {
			Integer realValue = data.get(key);
			return (Condition.EQUAL.equals(condition)) ? Integer.valueOf(value).equals(realValue) : !Integer.valueOf(value).equals(realValue);
		}

	}
	
	

	@Test
	public void testPredicateEvaluator() {

		Map<String, Integer> data = new HashMap<>();
		data.put("a", 5);
		data.put("b", 3);
		data.put("c", 8);
		data.put("d", 15);
		data.put("f", 18);
		data.put("g", 88);

		Evaluator evaluator = new Evaluator(data);

		String predicate;

		predicate = "(a==5 && (b==3 || d==115 || d==116) && f == 18) && c==8";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "a==35 && (c==9 && (a==5 || b==8 || d==111))";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "( a==5 || b==8) && (d==15 || f == 20)";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "a==5 && b==3 && d==15 && f == 18";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "a==50 || b==30 || d==15 || f == 180";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "( a==5 || b==8) && d==15 && f == 20";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "( a==5 || b==8 || f== 55) && d==15 && f == 20";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "(( (a==5 || b==8 || g==3) && c==8 ) && f == 18) && a==5";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "(a==5 || b==8) && c==9  && (d==15 || f == 20)";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "a == 5 && b==3 && d==16 && f == 18";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "a==5 && (c==8 || c== 11)";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "a==5 && (c==8 || c== 11 || a==5)";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "((a==5 || b==8 || g==3) && !(c==9 || f==15))  && f == 18 && a==5 && g==88";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));

		predicate = "a==50 || b==30 || d==15 || f == 180 && a==5 && b==3 && d==15 && f == 18";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));
		
		predicate = "a==50 || b==30 || d==15 || f == 180 && a==5 && b==3 && d==115 && f == 18";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));
		
		predicate = "(a==5 && b==3 && d==115) && f == 18 && a==50 || b==30 || d==15 || f == 180";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));
		
		predicate = "((a==5 || b==8 || g==3) && (c==8 || f==15))  && f == 18 && a==5 && g==88";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));
		
		
		predicate = "a==5 && b==33 || g==88";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));
		
		
		predicate = "a==5 || g==88 && b==33";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));

		
		predicate = "((a==5 || b==8 || g==3) && !(a==25 && c==9 || f==15))  && f == 18 && a==5 && g==88";
		Assert.assertTrue(evaluatePredicate(predicate, evaluator::evaluate));
		
		predicate = "((a==5 || b==8 || g==3) && (a==25 && c==9 || f==15))  && f == 18 && a==5 && g==88";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));
		
		predicate = "(((a==5 || b==8 || g==3) && (a==25 && c==9 || f==15))  && f == 18 && a==5) && g==88";
		Assert.assertFalse(evaluatePredicate(predicate, evaluator::evaluate));

	}

	public boolean evaluatePredicate(String predicate, Evaluate evaluator) {
		Queue<String> stackPredicates = new LinkedList<>();
		Queue<Operand> stackOperation = new LinkedList<>();
		buildPredicate(predicate, stackPredicates, stackOperation);
		if (stackOperation.isEmpty() && !stackPredicates.isEmpty()) {
			return evalute(stackPredicates.poll(), evaluator);
		}
		return runPredicate(stackPredicates, stackOperation, new Stack<>(), new Stack<>(), evaluator);
	}

	private void buildPredicate(String predicate, Queue<String> stackEvaluation, Queue<Operand> stackOperation) {
		Pattern pattern = Pattern.compile("\\(|\\)|\\|\\||&&|\\!\\(");
		Matcher matcher = pattern.matcher(predicate);
		String[] parts = pattern.split(predicate);

		for (int i = 0; i < parts.length; i++) {
			if (!parts[i].trim().isEmpty()) {
				stackEvaluation.add(parts[i].trim());
			}
			if (matcher.find()) {
				stackOperation.add(getOperation(matcher.group().trim()));
			}
		}
	}

	private Operand getOperation(String operation) {
		if ("||".equals(operation)) {
			return Operand.OR;
		} else if ("&&".equals(operation)) {
			return Operand.AND;
		} else if ("(".equals(operation)) {
			return Operand.OPEN_BRACKET;
		} else if (")".equals(operation)) {
			return Operand.CLOSE_BRACKET;
		} else if ("!(".equals(operation)) {
			return Operand.OPEN_BRACKET_WITH_EXCLAMATION;
		}
		throw new IllegalArgumentException("unknown operation: " + operation);
	}

	private boolean runPredicate(Queue<String> stackPredicates, Queue<Operand> stackOperation, Stack<Boolean> operandStack, Stack<Operand> braketsStack, Evaluate evaluator) {
		int sizeOperand = operandStack.size();
		while (!stackOperation.isEmpty()) {
			Operand op = stackOperation.poll();

			if (Operand.OPEN_BRACKET.equals(op)) {
				braketsStack.push(Operand.OPEN_BRACKET);
				boolean conditionResult = runPredicate(stackPredicates, stackOperation, operandStack, braketsStack, evaluator);
				operandStack.push(conditionResult);
				if (stackOperation.isEmpty()) {
					return evaluateOperandStack(operandStack);
				}
			} else if (Operand.OPEN_BRACKET_WITH_EXCLAMATION.equals(op)) {
				braketsStack.push(Operand.OPEN_BRACKET_WITH_EXCLAMATION);
				boolean conditionResult = !runPredicate(stackPredicates, stackOperation, operandStack, braketsStack, evaluator);
				operandStack.push(conditionResult);
				if (stackOperation.isEmpty()) {
					return evaluateOperandStack(operandStack);
				}

			} else if (Operand.CLOSE_BRACKET.equals(op)) {
				return evaluateOperandStack(operandStack);
			} else {
				boolean leftEvaluation;
				if (!operandStack.isEmpty()) {
					leftEvaluation = operandStack.pop();
				} else {
					leftEvaluation = evalute(stackPredicates.poll(), evaluator);
				}
				boolean rightEvaluation;

				if (Operand.OPEN_BRACKET.equals(stackOperation.peek())) {
					stackOperation.poll();
					braketsStack.push(Operand.OPEN_BRACKET);
					rightEvaluation = runPredicate(stackPredicates, stackOperation, operandStack, braketsStack, evaluator);
				} else if (Operand.OPEN_BRACKET_WITH_EXCLAMATION.equals(stackOperation.peek())) {
					stackOperation.poll();
					braketsStack.push(Operand.OPEN_BRACKET_WITH_EXCLAMATION);
					rightEvaluation = !runPredicate(stackPredicates, stackOperation, operandStack, braketsStack, evaluator);
				} else {
					rightEvaluation = evalute(stackPredicates.poll(), evaluator);
				}
				boolean evaluated = evaluateAndOrOperands(op, leftEvaluation, rightEvaluation);
				operandStack.push(evaluated);
				if (Operand.AND.equals(op) && !evaluated) {
					cleanStacks(braketsStack, stackPredicates, stackOperation, operandStack, sizeOperand);
					return false;
				}
			}
		}

		return evaluateOperandStack(operandStack);
	}

	private void cleanStacks(Stack<Operand> braketsStack, Queue<String> stackPredicates, Queue<Operand> stackOperation, Stack<Boolean> operandStack, int sizeOperand) {
		if (braketsStack.isEmpty()) {
			return;
		}
		while (!stackOperation.isEmpty() && !stackOperation.poll().equals(Operand.CLOSE_BRACKET)) {
			stackPredicates.poll();
		}
		while (operandStack.size() != sizeOperand) {
			operandStack.pop();
		}
		braketsStack.pop();

	}

	private boolean evaluateAndOrOperands(Operand op, boolean left, boolean right) {
		if (Operand.AND.equals(op)) {
			return left && right;
		} else if (Operand.OR.equals(op)) {
			return left || right;
		} else {
			return false;
		}
	}

	private boolean evaluateOperandStack(Stack<Boolean> operandStack) {
		while (!operandStack.isEmpty()) {
			if (!operandStack.pop()) {
				return false;
			}
		}
		return true;
	}

	private Boolean evalute(String subPredicate, Evaluate evaluate) {
		subPredicate = subPredicate.trim();
		Condition condition;
		if (subPredicate.contains("==")) {
			condition = Condition.EQUAL;
		} else if (subPredicate.contains("!=")) {
			condition = Condition.NOT_EQUAL;
		} else {
			throw new IllegalArgumentException("can't evaluate " + subPredicate);
		}

		String[] keyVal = (Condition.EQUAL.equals(condition)) ? subPredicate.split("==") : subPredicate.split("!=");
		String key = keyVal[0].trim();
		String value = keyVal[1].trim();
		if (NULL.equalsIgnoreCase(value)) {
			value = null;
		}
		return evaluate.apply(key, value, condition);
	}
}
