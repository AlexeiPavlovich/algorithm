package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import junit.framework.Assert;

public class TreeTest {

	@Test
	public void TestBinarySearchTree() {

		// System.out.println(Math.log(18));
		int[] num = { 20, 35, -15, 7, 5, 55, 1, -22, 60, 65, 77 };

		List<Integer> list = Arrays.stream(num).boxed().collect(Collectors.toList());
		Tree tree = new Tree();
		list.forEach(tree::add);

		System.out.println(tree.toList().toString());

		tree.rebalance(tree.root);

		System.out.println(tree.toList().toString());

		System.out.println("........................");

		tree.rebalance(tree.root);

		System.out.println("height root:" + tree.getHeightRec(tree.root));
		System.out.println("height right:" + tree.getHeightRec(tree.root.getRight()));
		System.out.println("height left:" + tree.getHeightRec(tree.root.getLeft()));

		System.out.println(tree.toList().toString());
		int treeLen = tree.len();
		System.out.println("len:" + treeLen);

		for (int i = 0; i < treeLen; i++) {
			System.out.println("index:" + i + "=" + tree.getAtIndex(i));
		}

		System.out.println("........................");

		System.out.println(tree.isBST());
		for (int i = 0; i < num.length; i++) {
			Assert.assertTrue(tree.contains(num[i]));
		}

		System.out.println(tree.getMin());

		System.out.println(tree.getMax());

		System.out.println("delete 15");

		tree.delete(-15);

		tree.print();

		System.out.println("delete 35");

		tree.delete(35);

		tree.print();

		System.out.println("delete -22");

		tree.delete(-22);

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

		public Integer getMin() {
			return getMinRec(root);
		}

		public Integer getMax() {
			return getMaxRec(root);
		}

		public int len() {
			return lenRec(root);
		}

		public List<Integer> toList() {
			List<Integer> lst = new ArrayList<>();
			toListRec(root, lst);
			return lst;
		}

		private void toListRec(Node currNode, List<Integer> lst) {
			if (currNode != null) {
				toListRec(currNode.getLeft(), lst);
				lst.add(currNode.getData());
				toListRec(currNode.getRight(), lst);
			}
		}

		public int getAtIndex(int index) {
			return getAtIndexRec(root, index);
		}

		private void rebalance(Node currNode) {

			if (currNode == null) {
				return;
			}
			rebalance(currNode.getLeft());
			rebalance(currNode.getRight());

			if (currNode.getLeft() != null || currNode.getRight() != null) {
				int hLeft = getHeightRec(currNode.getLeft());
				int hRight = getHeightRec(currNode.getRight());
				int balanceFactor = hLeft- hRight;
				if (Math.abs(balanceFactor) > 1) {
					System.out.println("recalc balance for " + currNode.getData() + " balance factor:" + balanceFactor
							+ " left h:" + hLeft + " h right: " + hRight);

					if (balanceFactor > 0) {
						rotateRight(currNode);
					} else{
						rotateLeft(currNode);
					}

				}

			}

		}



		private void rotateLeft(Node currNode) {
			//System.out.println("**********************************************************");
			//printRec(currNode);
			rotate(currNode,false);
			//System.out.println("rotated:");
			//printRec(currNode);
			//System.out.println("**********************************************************");
		}

		private void rotateRight(Node currNode) {
			//System.out.println("**********************************************************");	
			//printRec(currNode);
			rotate(currNode,true);
			//System.out.println("rotated:");
			//printRec(currNode);
			//System.out.println("**********************************************************");		
		}
		
		
		private void rotate(Node rootNode,boolean rightRotation) {
			
			
			int savedData=rootNode.getData();
			
			if(rightRotation) {
				Node leftRight=rootNode.getLeft().getRight();
				rootNode.setData(rootNode.getLeft().getData());
				rootNode.setRight(rootNode.getLeft());
				rootNode.getRight().setData(savedData);
				rootNode.setLeft(rootNode.getLeft().getLeft());
				rootNode.getRight().setLeft(leftRight);
				rootNode.getLeft().setRight(null);
			}
			else {
				Node rightLeft=rootNode.getRight().getLeft();

				rootNode.setData(rootNode.getRight().getData());
				rootNode.setLeft(rootNode.getRight());
				rootNode.getLeft().setData(savedData);
				rootNode.setRight(rootNode.getRight().getRight());
				rootNode.getLeft().setRight(rightLeft);
				rootNode.getLeft().setLeft(null);
			}
		}

		private int getHeightRec(Node currNode) {
			if (currNode == null) {
				return -1;
			}

			return Math.max(getHeightRec(currNode.getLeft()), getHeightRec(currNode.getRight())) + 1;
		}

		private int getAtIndexRec(Node currNode, int index) {

			if (currNode == null) {
				return Integer.MIN_VALUE;
			}
			int leftHeight = lenRec(currNode.getLeft());

			if (leftHeight == index) {
				return currNode.getData();
			} else if (leftHeight > index) {
				return getAtIndexRec(currNode.getLeft(), index);
			}

			return getAtIndexRec(currNode.getRight(), index - leftHeight - 1);

		}

		private int lenRec(Node currNode) {
			if (currNode == null) {
				return 0;
			}
			return lenRec(currNode.getLeft()) + lenRec(currNode.getRight()) + 1;
		}

		public boolean contains(Integer data) {
			return searchRec(root, data) != null;
		}

		public void delete(Integer data) {
			root = deleteRec(root, data);
		}

		public void add(Integer data) {
			root = insertRec(root, data);
		}

		public void print() {
			printRec(root);
		}

		public boolean isBST() {
			return isBSTRec(root);
		}

		private Node deleteRec(Node currNode, Integer data) {
			if (currNode == null) {
				return null;
			}
			if (data.compareTo(currNode.getData()) > 0) {
				currNode.setRight(deleteRec(currNode.getRight(), data));
			} else if (data.compareTo(currNode.getData()) < 0) {
				currNode.setLeft(deleteRec(currNode.getLeft(), data));
			} else {
				// found
				if (currNode.getLeft() == null) {
					return currNode.getRight();
				} else if (currNode.getRight() == null) {
					return currNode.getLeft();
				}

				currNode.setData(getMinRec(currNode.getRight()));
				currNode.setRight(deleteRec(currNode.getRight(), currNode.getData()));
			}
			return currNode;
		}

		private Node searchRec(Node node, Integer data) {
			if (node == null) {
				return null;
			}
			int cmp = data.compareTo(node.getData());
			if (cmp == 0) {
				return node;
			} else if (cmp > 0) {
				return searchRec(node.getRight(), data);
			}
			return searchRec(node.getLeft(), data);
		}

		public boolean isBSTRec(Node node) {
			if (node == null) {
				return true;
			}
			if (node.getLeft() != null && getMaxRec(node.getLeft()).compareTo(node.getData()) > 0) {
				return false;
			}
			if (node.getRight() != null && getMinRec(node.getRight()).compareTo(node.getData()) < 0) {
				return false;
			}
			return isBSTRec(node.getLeft()) && isBSTRec(node.getRight());
		}

		private Integer getMinRec(Node node) {
			if (node == null) {
				return Integer.MAX_VALUE;
			}
			return Math.min(node.getData(), Math.min(getMinRec(node.getLeft()), getMinRec(node.getRight())));
		}

		private Integer getMaxRec(Node node) {
			if (node == null) {
				return Integer.MIN_VALUE;
			}
			return Math.max(node.getData(), Math.max(getMaxRec(node.getLeft()), getMaxRec(node.getRight())));
		}

		private void printRec(Node node) {
			if (node == null) {
				return;
			}
			printRec(node.getLeft());
			System.out.println(node.getData());
			printRec(node.getRight());

		}

		private Node insertRec(Node node, Integer data) {
			if (node == null) {
				node = new Node(data);
			} else if (data.compareTo(node.getData()) < 0) {
				node.setLeft(insertRec(node.getLeft(), data));
			} else {
				node.setRight(insertRec(node.getRight(), data));
			}
			return node;
		}
	}

}
