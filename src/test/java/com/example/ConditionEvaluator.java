package com.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class ConditionEvaluator {
	
	
	@Test
	public void testPredicateEvaluator() {

		Map<String, Integer> data = new HashMap<>();
		data.put("a", 5);
		data.put("b", 3);
		data.put("c", 8);
		data.put("d", 15);
		data.put("f", 18);

		String predicate1 = "(a==5 || b==3) && d==15 || f == 18";

		Assert.assertTrue(evaluateCondition(predicate1, data));

	}
	
	
	
    private boolean evaluateCondition(String condition, Map<String, Integer> data) {
        Stack<Character> operatorStack = new Stack<>();
        Stack<Boolean> operandStack = new Stack<>();

        for (int i = 0; i < condition.length(); i++) {
            char ch = condition.charAt(i);

            if (ch == '(') {
                operatorStack.push(ch);
            } else if (ch == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    evaluateTopOperator(operatorStack, operandStack);
                }
                operatorStack.pop(); // Pop the '('
            } else if (ch == '&' || ch == '|') {
                while (!operatorStack.isEmpty() && hasHigherPrecedence(ch, operatorStack.peek())) {
                    evaluateTopOperator(operatorStack, operandStack);
                }
                operatorStack.push(ch);
            } else if (Character.isLetter(ch)) {
                String fieldName = Character.toString(ch);
                int value = data.get(fieldName);
                operandStack.push(value == 1); // Modify this as needed based on your logic
            }
        }

        while (!operatorStack.isEmpty()) {
            evaluateTopOperator(operatorStack, operandStack);
        }

        return operandStack.pop();
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        return (op1 == '&' && op2 == '|');
    }

    private static void evaluateTopOperator(Stack<Character> operatorStack, Stack<Boolean> operandStack) {
        char operator = operatorStack.pop();
        boolean operand2 = operandStack.pop();
        boolean operand1 = operandStack.pop();
        System.out.print(operand1+" "+operator+ " "+operand2);

        if (operator == '&') {
            operandStack.push(operand1 && operand2);
        } else if (operator == '|') {
            operandStack.push(operand1 || operand2);
        }
    }

   
}
