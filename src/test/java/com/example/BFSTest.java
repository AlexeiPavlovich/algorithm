package com.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class BFSTest {

	@Test
	public void TestBreadthFirstSearch() {

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
			Queue<Vertex> q = new LinkedList<>();
			q.add(root);
			while (!q.isEmpty()) {
				Vertex curr = q.remove();
				System.out.println(curr.getName());
				curr.setVisited(true);
				curr.getAdjacencyList().stream().filter(v -> !v.isVisited()).forEach(q::add);

			}
		}
	}
}
