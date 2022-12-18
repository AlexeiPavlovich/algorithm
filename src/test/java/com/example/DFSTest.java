package com.example;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

public class DFSTest {
	@Test
	public void TestDepthFirstSearch() {

		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		Vertex g = new Vertex("G");
		Vertex h = new Vertex("H");

		a.getAdjacencyList().addAll(Arrays.asList(b, f, g));
		b.getAdjacencyList().addAll(Arrays.asList(a, c, d));
		c.getAdjacencyList().addAll(Arrays.asList(b));
		d.getAdjacencyList().addAll(Arrays.asList(b, e));
		e.getAdjacencyList().addAll(Arrays.asList(d));
		f.getAdjacencyList().addAll(Arrays.asList(a));
		g.getAdjacencyList().addAll(Arrays.asList(a, h));
		h.getAdjacencyList().addAll(Arrays.asList(g));

		BreadthFirstSearch bfs = new BreadthFirstSearch();
		bfs.traverce(a);
	}

	private static class BreadthFirstSearch {

		public void traverce(Vertex root) {
			Stack<Vertex> stack = new Stack<>();
			stack.add(root);
			while (!stack.isEmpty()) {
				Vertex curr = stack.pop();
				System.out.println(curr.getName());
				curr.setVisited(true);
				curr.getAdjacencyList().stream().filter(v -> !v.isVisited()).forEach(stack::add);

			}
		}
	}
}
