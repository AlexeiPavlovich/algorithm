package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class TreeTest {

	@Test
	public void TestBinarySearchTree() {
		
		System.out.println(Math.log(18));
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };
		List<Integer> list = Arrays.stream(num).boxed().collect(Collectors.toList());
		Tree tree = new Tree();
		list.forEach(tree::add);
		tree.print();
		System.out.println(tree.isBST());
		/*
		 * for (int i = 0; i < num.length; i++) { int found = tree.search(num[i]);
		 * Assert.assertEquals(num[i], found); } System.out.println(tree.getSorted());
		 */
	}

	public static class Node {

		private Node left;
		private Node right;

		private Integer data;

		public Node() {

		}

		public Node(Integer data) {
			setData(data);
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Integer getData() {
			return data;
		}

		public void setData(Integer data) {
			this.data = data;
		}

	}

	private static class Tree {
		private Node root;

		public void print() {
			printRec(root);
		}
		
		//public searchRec(Integer item, Node currentNode)
		
		private Node searchRec(Integer item, Node currentNode) {
			if (currentNode == null) {
				return null;
			} else if (currentNode.data.equals(item)) {
				return currentNode;
			} else if (item.compareTo(currentNode.data) < 0) {
				return searchRec(item, currentNode.getLeft());
			} else {
				return searchRec(item, currentNode.getRight());
			}
		}

		private Integer getMin(Node node) {
			if (node == null) {
				return Integer.MAX_VALUE;
			}
			return Math.min(node.getData(), Math.min(getMin(node.getLeft()), (getMin(node.getRight()))));
		}

		private Integer getMax(Node node) {
			if (node == null) {
				return Integer.MIN_VALUE;
			}
			return Math.max(node.getData(), Math.max(getMax(node.getLeft()), (getMax(node.getRight()))));
		}

		private int isBST() {
			return isBSTRec(root);
		}

		private int isBSTRec(Node node) {
			if (node == null) {
				return 1;
			}

			/* false if the max of the left is > than us */
			if (node.getLeft() != null && getMax(node.getLeft()).compareTo(node.getData()) > 0) {
				return 0;
			}

			/* false if the min of the right is <= than us */
			if (node.getRight() != null && getMin(node.getRight()).compareTo(node.getData()) < 0) {
				return 0;
			}

			/*
			 * false if, recursively, the left or right is not a BST
			 */
			if (isBSTRec(node.getLeft()) != 1 || isBSTRec(node.getRight()) != 1) {
				return 0;
			}

			/* passing all that, it's a BST */
			return 1;
		}

		private void printRec(Node node) {
			if (node == null) {
				return;
			}
			printRec(node.getLeft());
			System.out.println(node.getData());
			printRec(node.getRight());
		}

		public void add(Integer data) {
			root = addRec(root, data);
		}

		private Node addRec(Node node, Integer data) {
			if (node == null) {
				return new Node(data);
			}
			if (node.getData().compareTo(data) < 0) {
				node.setLeft(addRec(node.getLeft(), data));
			} else {
				node.setRight(addRec(node.getRight(), data));
			}
			return node;
		}

	}

}
