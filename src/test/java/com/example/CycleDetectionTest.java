package com.example;

import java.util.Arrays;

import org.junit.Test;

public class CycleDetectionTest {

	
	@Test
	public void TestCycleDetection() {
		Vertex d = new Vertex("D");
		Vertex a = new Vertex("A");
		Vertex e = new Vertex("E");
		Vertex c = new Vertex("C");
		Vertex b = new Vertex("B");
		Vertex d1 = new Vertex("D1");
		d.getAdjacencyList().add(a);
		a.getAdjacencyList().addAll(Arrays.asList(c, e));
		e.getAdjacencyList().add(d);
		c.getAdjacencyList().addAll(Arrays.asList(b, d1));
		detectCycle(d);
	}

	private void detectCycle(Vertex vertex) {
		System.out.println("parent:"+vertex.getName());
		vertex.setBeingVisited(true);
		for (Vertex child : vertex.getAdjacencyList()) {
			System.out.println("child:"+child.getName());
			if (child.isBeingVisited()) {
				System.out.println("backward edge ... There is a cycle from " + vertex.getName() + " to " + child.getName());
				return;
			}
			if (!child.isVisited()) {
				detectCycle(child);
			}
		}
		System.out.println("backtrack:" + vertex.getName());
		vertex.setVisited(true);

	}

}
