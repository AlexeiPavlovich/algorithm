package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.junit.Test;

import junit.framework.Assert;

public class TreeTest {

	@Test
	public void TestBinarySearchTree() {
		
		//System.out.println(Math.log(18));
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };
	

		
		List<Integer> list = Arrays.stream(num).boxed().collect(Collectors.toList());
		Tree tree = new Tree();
		list.forEach(tree::add);
		tree.print();
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
		
		public boolean isBST(){
			return isBSTRec(root);
		}
		
		
		private Node deleteRec(Node currNode, Integer data) {
			if(currNode==null) {
				return null;
			}
			if(data.compareTo(currNode.getData()) > 0) {
				currNode.setRight(deleteRec(currNode.getRight(), data));
			}
			else if(data.compareTo(currNode.getData()) < 0) {
				currNode.setLeft(deleteRec(currNode.getLeft(), data));
			}
			else {
				//found
				if(currNode.getLeft() == null ) {
					return currNode.getRight();
				}
				else if(currNode.getRight() == null ) {
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
		
		
		
		public boolean isBSTRec(Node node){
			if(node==null) {
				return true;
			}
			if(node.getLeft()!=null && getMaxRec(node.getLeft()).compareTo(node.getData()) > 0 ) {
				return false;
			}
			if(node.getRight()!=null && getMinRec(node.getRight()).compareTo(node.getData()) < 0 ) {
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
			if(node==null) {
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
			} else{
				node.setRight(insertRec(node.getRight(), data));
			}
			return node;
		}
	}

}
