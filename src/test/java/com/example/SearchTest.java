package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import junit.framework.Assert;

public class SearchTest {
	
	@Test
	public void TestTowersOfHanoi() {
		//moveDisks(2, 'A', 'B', 'C');
		
	 GCD(24,9);
	}
	
	private void GCD(int i, int j) {
		if(j==0) {
			System.out.println("found "+i);
			return;
		}
		

		GCD(j, i % j);
		
	}

	private String getDiskName(int disk) {
		if(disk==0) {
			return "small";
		}
		else if(disk==1) {
			return "medium";
		}
		return "large";
	}

	void moveDisks(int disk, char source, char middle, char destination) {
		if (getDiskName(disk).equals("small")) {
			System.out.println("Plate " + getDiskName(disk) + " from " + source + " to " + destination);
			return;
		}
		moveDisks(disk - 1, source, destination, middle);
		System.out.println("Plate " + getDiskName(disk) + " from " + source + " to " + destination);
		moveDisks(disk - 1, middle, source, destination);
	}

	@Test
	public void TestBinarySearch() {
		int[] num = { 1, 2, 3, 4, 5, 6, 7 };

		for (int i = 0; i < num.length; i++) {
			int foundIndex = binarySearch(num, num[i]);
			Assert.assertEquals(i, foundIndex);
		}

	}
	
	
	
	@Test
	public void firstRepeatingCharacter() {
		String input= "inside code";
		Map<Character, Long> reapeatCharacters=input.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()));

		System.out.println(reapeatCharacters);
	}

	private int binarySearch(int[] arr, int valToFind) {

		int start = 0;
		int end = arr.length;

		while (true) {
			int mid = (end - start) / 2 + start;
			if (arr[mid] == valToFind) {
				return mid;
			}
			if (arr[mid] > valToFind) {
				end = mid;
			} else if (arr[mid] < valToFind) {
				start = mid;
			}

		}

	}

	@Test
	public void TestBinarySearchTree() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };
		List<Integer> list = Arrays.stream(num).boxed().collect(Collectors.toList());
		Tree<Integer> tree = new Tree<Integer>();
		list.forEach(tree::add);
		tree.print();
		for (int i = 0; i < num.length; i++) {
			int found = tree.search(num[i]);
			Assert.assertEquals(num[i], found);
		}
		System.out.println(tree.getSorted());

	}

	private static class Tree<T extends Comparable<T>> {
		TreeNode<T> root;
		
		public List<T> getSorted() {
			return getSortedRec(root);
		}

		private List<T> getSortedRec(TreeNode<T> currNode) {
			List<T> lst = new ArrayList<>();
			if (currNode == null) {
				return lst;
			}
			lst.addAll(getSortedRec(currNode.left));
			lst.add(currNode.data);
			lst.addAll(getSortedRec(currNode.right));
			return lst;
		}

		public T search(T item) {
			TreeNode<T> node = searchRec(item, root);
			return (node != null) ? node.data : null;
		}

		private TreeNode<T> searchRec(T item, TreeNode<T> currentNode) {
			if (currentNode == null) {
				return null;
			} else if (currentNode.data.equals(item)) {
				return currentNode;
			} else if (item.compareTo(currentNode.data) < 0) {
				return searchRec(item, currentNode.left);
			} else {
				return searchRec(item, currentNode.right);
			}

		}

		public void print() {
			printRec(root);
		}

		private void printRec(TreeNode<T> currNode) {
			if (currNode == null) {
				return;
			}
			printRec(currNode.left);
			System.out.println(currNode.data);
			printRec(currNode.right);
		}

		public void add(T item) {
			root = insertRec(item, root);
		}

		private TreeNode<T> insertRec(T item, TreeNode<T> currNode) {
			if (currNode == null) {
				return new TreeNode<>(item);
			} else if (item.compareTo(currNode.data) < 0) {
				currNode.left = insertRec(item, currNode.left);
			} else {
				currNode.right = insertRec(item, currNode.right);
			}
			return currNode;
		}

	}

	private static class TreeNode<T extends Comparable<T>> {

		TreeNode(T data) {
			this.data = data;
		}

		T data;
		TreeNode<T> left;
		TreeNode<T> right;
	}
}
