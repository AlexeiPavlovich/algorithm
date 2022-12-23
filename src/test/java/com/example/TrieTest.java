package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Stream;

import org.junit.Test;

import junit.framework.Assert;

public class TrieTest {

	@Test
	public void TestTrie() {
		Trie trie = new Trie();
		List<String> words = Arrays.asList("apir", "apple", "approve", "bee");
		words.forEach(trie::insert);

		words.forEach(word -> Assert.assertTrue(trie.exists(word)));

		Assert.assertFalse(trie.exists("adam"));

		System.out.println(trie.autocomlete("app").toString());
		System.out.println(trie.autocomlete("ap").toString());
		System.out.println(trie.getAll().toString());

	}

	public static class Trie {

		private Node rootNode;

		public Trie() {
			rootNode = new Node("");
		}

		public List<String> autocomlete(String prefix) {
			resetVisited();
			Queue<Integer> prefixQueue = new LinkedList<>();
			for (int i = 0; i < prefix.length(); i++) {
				char c = prefix.charAt(i);
				int asciiIndex = c - 'a';
				prefixQueue.add(asciiIndex);
			}
			return autocomleteRec(rootNode, prefixQueue, "");
		}
		public List<String> getAll() {
			return autocomlete(""); 
		}
		

		private void resetVisited() {
			Stack<Node> stack = new Stack<>();
			stack.add(rootNode);

			while (!stack.isEmpty()) {
				Node currNode = stack.pop();
				currNode.setVisited(false);
				Stream.of(currNode.getChildren()).filter(Objects::nonNull).filter(n -> n.isVisited())
						.forEach(stack::add);
			}
		}

		private List<String> autocomleteRec(Node currNode, Queue<Integer> prefixQueue, String word) {
			List<String> words = new ArrayList<>();
			if (currNode == null || currNode.isVisited()) {
				return words;
			}

			currNode.setVisited(true);
			word += String.valueOf(currNode.getCharacter());
			if (currNode.isLeaf()) {
				words.add(word);
			}
			if (!prefixQueue.isEmpty()) {
				int asciiIndex = prefixQueue.poll();
				Node child = currNode.getChild(asciiIndex);
				if (child != null && !child.isVisited()) {
					words.addAll(autocomleteRec(child, prefixQueue, word));
				}
			} else {
				for (int i = 0; i < currNode.getChildren().length; i++) {
					words.addAll(autocomleteRec(currNode.getChildren()[i], prefixQueue, word));
				}
			}
			return words;
		}

		public void insert(String word) {
			word = word.toLowerCase();
			Node tempNode = rootNode;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				int asciiIndex = c - 'a';
				Node node = tempNode.getChild(asciiIndex);
				if (node == null) {
					node = new Node(String.valueOf(c));
					tempNode.setChild(asciiIndex, node);
				}
				tempNode = node;
			}
			tempNode.setLeaf(true);
		}

		public boolean exists(String word) {
			word = word.toLowerCase();
			Node tempNode = rootNode;
			for (int i = 0; i < word.length() && tempNode != null; i++) {
				char c = word.charAt(i);
				int asciiIndex = c - 'a';
				tempNode = tempNode.getChild(asciiIndex);

			}
			return tempNode != null && tempNode.isLeaf();

		}
	}

	private static class Constants {
		private Constants() {

		}

		private static int ALPPHABET_SIZE = 26;
	}

	private static class Node {

		private String character;
		private Node[] children;
		private boolean isLeaf;
		private boolean visited;

		public Node(String character) {
			setCharacter(character);
			children = new Node[Constants.ALPPHABET_SIZE];
		}

		public Node getChild(int index) {
			return getChildren()[index];
		}

		public void setChild(int index, Node child) {
			getChildren()[index] = child;
		}

		public String getCharacter() {
			return character;
		}

		public void setCharacter(String character) {
			this.character = character;
		}

		public Node[] getChildren() {
			return children;
		}

		public boolean isLeaf() {
			return isLeaf;
		}

		public void setLeaf(boolean isLeaf) {
			this.isLeaf = isLeaf;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

		public String toString() {
			return getCharacter();
		}

	}

}
