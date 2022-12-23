package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

public class AVLTreeTest {
	@Test
	public void TestBinarySearchTree() {

		// System.out.println(Math.log(18));
		int[] num = { 1, 2, 3, 4, 5, 6, 7, 8 };

		List<Integer> list = Arrays.stream(num).boxed().collect(Collectors.toList());
		AVLTree tree = new AVLTree();

		list.forEach(tree::add);

		tree.print();

	}

	public static class Node {

		private Node right;
		private Node left;
		private int height;

		private Integer data;

		public Node() {

		}

		public Node(Integer data) {
			setData(data);
			setHeight(0);
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
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

		@Override
		public String toString() {
			return "data: " + getData() + " height:" + getHeight();
		}

	}

	private static class AVLTree {

		private Node root;

		public void add(Integer data) {
			root = addRec(root, data);
		}

		public void print() {
			printRec(root);
		}

		private void printRec(Node node) {
			if (node == null) {
				return;
			}
			printRec(node.getLeft());
			System.out.println(node.toString());
			printRec(node.getRight());

		}

		private int getHeight(Node node) {
			return (node == null) ? -1 : node.getHeight();
		}

		private Node addRec(Node currNode, Integer data) {
			if (currNode == null) {
				return new Node(data);
			} else if (data.compareTo(currNode.getData()) < 0) {
				currNode.setLeft(addRec(currNode.getLeft(), data));
				if (getHeight(currNode.getLeft()) - getHeight(currNode.getRight()) == 2) {
					currNode = rotateLeft(currNode);

				}
			} else if (data.compareTo(currNode.getData()) > 0) {
				currNode.setRight(addRec(currNode.getRight(), data));

				if (getHeight(currNode.getRight()) - getHeight(currNode.getLeft()) == 2) {
					currNode = rotateRight(currNode);
				}
			}

			currNode.setHeight(Math.max(getHeight(currNode.getLeft()), getHeight(currNode.getRight())) + 1);

			return currNode;
		}

		private Node rotateRight(Node currNode) {
			if (currNode.getRight().getLeft() != null) {
				return doubleWithRightChild(currNode);
			} else {
				return rotateWithRightChild(currNode);
			}

		}

		private Node rotateWithRightChild(Node currNode) {
			Node nodeRight = currNode.getRight();
			currNode.setRight(nodeRight.getLeft());
			nodeRight.setLeft(currNode);

			currNode.setHeight(Math.max(getHeight(currNode.getLeft()), getHeight(currNode.getRight())) + 1);
			nodeRight.setHeight(Math.max(getHeight(nodeRight.getRight()), currNode.getHeight()) + 1);

			return nodeRight;
		}

		private Node doubleWithRightChild(Node currNode) {
			currNode.setRight(rotateWithLeftChild(currNode.getRight()));
			return rotateWithRightChild(currNode);
		}

		private Node rotateWithLeftChild(Node currNode) {
			Node nodeLeft = currNode.getLeft();
			currNode.setLeft(nodeLeft.getRight());
			nodeLeft.setRight(currNode);

			currNode.setHeight(Math.max(getHeight(currNode.getLeft()), getHeight(currNode.getRight())) + 1);
			nodeLeft.setHeight(Math.max(getHeight(nodeLeft.getLeft()), currNode.getHeight()) + 1);

			return nodeLeft;
		}

		private Node doubleWithLeftChild(Node currNode) {
			currNode.setLeft(rotateWithRightChild(currNode.getLeft()));
			return rotateWithLeftChild(currNode);
		}

		private Node rotateLeft(Node currNode) {
			if (currNode.getLeft().getRight() != null) {
				return doubleWithLeftChild(currNode);
			} else {
				return rotateWithLeftChild(currNode);
			}
		}

	}
}
