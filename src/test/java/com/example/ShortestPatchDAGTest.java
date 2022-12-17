package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;

import org.junit.Test;

public class ShortestPatchDAGTest {

	@Test
	public void TestShortestPatchDAG() {
		Vertex s = new Vertex("S");
		Vertex a = new Vertex("A");
		Vertex c = new Vertex("C");
		Vertex b = new Vertex("B");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");

		Edge edgeSA = new Edge(a, 1);
		Edge edgeSC = new Edge(c, 2);

		Edge edgeAB = new Edge(b, 6);

		Edge edgeCA = new Edge(a, 4);
		Edge edgeCD = new Edge(d, 3);

		Edge edgeBE = new Edge(e, 2);
		Edge edgeBD = new Edge(d, 1);

		Edge edgeDE = new Edge(e, 1);

		s.getAdjacencyEdgeList().addAll(Arrays.asList(edgeSA, edgeSC));

		a.getAdjacencyEdgeList().addAll(Arrays.asList(edgeAB));

		c.getAdjacencyEdgeList().addAll(Arrays.asList(edgeCA, edgeCD));

		b.getAdjacencyEdgeList().addAll(Arrays.asList(edgeBE, edgeBD));

		d.getAdjacencyEdgeList().addAll(Arrays.asList(edgeDE));

		s.setMinDistance(0);

		TopologicalSearch topologicalSearch = new TopologicalSearch();

		List<Vertex> graph = Arrays.asList(s, a, c, b, d, e);

		Stack<Vertex> stack = topologicalSearch.apply(graph);

		while (!stack.isEmpty()) {
			Vertex currVertex = stack.pop();
			System.out.println("currVertex: " + currVertex);
			currVertex.getAdjacencyEdgeList().forEach(edge -> {
				Vertex targetVertex = edge.getTargetVertex();
				System.out.print("targetVertex:" + targetVertex + "  distance: " + targetVertex.getMinDistance());
				int recalcDistance = currVertex.getMinDistance() + edge.getWidth();
				if (recalcDistance < targetVertex.getMinDistance()) {
					targetVertex.setMinDistance(recalcDistance);
					targetVertex.setPredecessor(currVertex);
					System.out.println(" recalc  distance: " + recalcDistance);
				}
				System.out.println();
			});
			System.out.println();
		}

		System.out.println("**********************");
		System.out.println();
		graph.forEach(v -> System.out.println(v + " distance: " + v.getMinDistance()));

	}

	private static class TopologicalSearch {

		private Predicate<Vertex> isNotVisited = v -> !v.isVisited();

		public Stack<Vertex> apply(List<Vertex> graph) {
			Stack<Vertex> stack = new Stack<>();
			graph.stream().filter(isNotVisited).forEach(v -> dfs(v, stack));
			return stack;
		}

		public void dfs(Vertex vertex, Stack<Vertex> stack) {
			vertex.setVisited(true);
			vertex.getAdjacencyEdgeList().stream().map(Edge::getTargetVertex).filter(isNotVisited)
					.forEach(v -> dfs(v, stack));
			stack.push(vertex);
		}

	}
}
