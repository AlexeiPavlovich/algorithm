package com.example;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Test;

public class DijkstraAlgoritmTest {

	@Test
	public void TestShortestPatchDAG() {
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex h = new Vertex("H");
		Vertex e = new Vertex("E");
		Vertex d = new Vertex("D");
		Vertex c = new Vertex("C");
		Vertex f = new Vertex("F");
		Vertex g = new Vertex("G");

		Edge edgeAB = new Edge(b, 5);
		Edge edgeAE = new Edge(e, 9);
		Edge edgeAH = new Edge(h, 8);

		Edge edgeBD = new Edge(d, 15);
		Edge edgeBC = new Edge(c, 12);
		Edge edgeBH = new Edge(h, 4);

		Edge edgeHF = new Edge(f, 6);
		Edge edgeHC = new Edge(c, 7);

		Edge edgeEH = new Edge(h, 5);
		Edge edgeEF = new Edge(f, 4);
		Edge edgeEG = new Edge(g, 20);

		Edge edgeDG = new Edge(g, 9);

		Edge edgeCD = new Edge(d, 3);
		Edge edgeCG = new Edge(g, 11);

		Edge edgeFG = new Edge(g, 13);
		Edge edgeFC = new Edge(c, 1);

		a.getAdjacencyEdgeList().addAll(Arrays.asList(edgeAB, edgeAE, edgeAH));
		b.getAdjacencyEdgeList().addAll(Arrays.asList(edgeBD, edgeBC, edgeBH));
		h.getAdjacencyEdgeList().addAll(Arrays.asList(edgeHF,edgeHC));
		e.getAdjacencyEdgeList().addAll(Arrays.asList(edgeEH, edgeEF, edgeEG));
		d.getAdjacencyEdgeList().addAll(Arrays.asList(edgeDG));
		c.getAdjacencyEdgeList().addAll(Arrays.asList(edgeCD, edgeCG));
		f.getAdjacencyEdgeList().addAll(Arrays.asList(edgeFG,edgeFC));

		computePath(a);
		System.out.println(g);
	}

	public void computePath(Vertex source) {
		source.setMinDistance(0);
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.add(source);
		while (!queue.isEmpty()) {
			System.out.println(queue.toString());
			Vertex actualVertex = queue.poll();
			System.out.println("actualVertex: "+actualVertex);
			actualVertex.getAdjacencyEdgeList().forEach(edge -> {
				Vertex targetVertex = edge.getTargetVertex();
				int recalcDistance = actualVertex.getMinDistance() + edge.getWidth();
				if (recalcDistance < targetVertex.getMinDistance()) {
					System.out.println("targetVertex recalc "+targetVertex +" to "+recalcDistance);
					queue.remove(targetVertex);
					targetVertex.setMinDistance(recalcDistance);
					targetVertex.setPredecessor(actualVertex);
					queue.add(targetVertex);
				}
			});
		}

	}

}
