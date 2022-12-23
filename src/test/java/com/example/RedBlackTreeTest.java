package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class RedBlackTreeTest {
	@Test
	public void TestBinarySearchTree() {

		// System.out.println(Math.log(18));
		int[] num = { 1, 2, 3, 4, 5, 6, 7, 8 };

		List<Integer> list = Arrays.stream(num).boxed().collect(Collectors.toList());
		RedBlackTree tree = new RedBlackTree();

		list.forEach(tree::add);

		tree.print();

	}

	public static class Node {

		public enum Color {
			Red, Black
		};

		private Node parent;
		private Node left;
		private Node right;
		private Integer data;
		private Color color;

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public Node() {

		}

		public Node(Integer data, Node parent, Color color) {
			setData(data);
			setParent(parent);
			setColor(color);
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
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
			return "data: " + getData();
		}

	}

	private static class RedBlackTree {

		private Node root;

		public void add(Integer data) {
			Node node = root;
			Node parent = null;
			while (node != null) {
				parent = node;
				if (data.compareTo(node.getData()) < 0) {
					node = node.getLeft();
				} else if (data.compareTo(node.getData()) > 0) {
					node = node.getRight();
				}
			}

			Node newNode = new Node(data, parent, Node.Color.Red);
			if (parent == null) {
				root = newNode;
			} else if (data.compareTo(parent.getData()) < 0) {
				parent.setLeft(newNode);
			} else if (data.compareTo(parent.getData()) > 0) {
				parent.setRight(newNode);
			}
			fixRedBlackPropertiesAfterInsert(newNode);

		}

		private void fixRedBlackPropertiesAfterInsert(Node newNode) {
			// TODO Auto-generated method stub

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

	}
}
