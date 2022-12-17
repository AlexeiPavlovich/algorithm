package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;

import org.junit.Test;

public class TopologicalOrderingTest {
	
	private static Predicate<Vertex> isNotVisited = v -> !v.isVisited();
	
	
	@Test
	public void TestTopologicalOrder() {

		Vertex v0 = new Vertex("0");
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		Vertex v4 = new Vertex("4");
		Vertex v5 = new Vertex("5");
		
		List<Vertex> graph = new ArrayList<>();

		v2.getAdjacencyList().addAll(Arrays.asList(v3));
		v3.getAdjacencyList().addAll(Arrays.asList(v1));
		v4.getAdjacencyList().addAll(Arrays.asList(v0,v1));

		v5.getAdjacencyList().addAll(Arrays.asList(v0,v2));
		
		
		
		graph.addAll(Arrays.asList(v2,v3,v4,v5));
		
		TopologicalSearch search= new TopologicalSearch();
		
		graph.stream().filter(isNotVisited).forEach(search::dfs);
		
		
		while(!search.getStack().isEmpty()) {
			System.out.println(search.getStack().pop());
		}
		
	
		


	}
	
	
	private static class TopologicalSearch{
		private Stack<Vertex> stack= new Stack<>();
		
		public void dfs(Vertex vertex) {
			vertex.setVisited(true);
			vertex.getAdjacencyList().stream().filter(isNotVisited).forEach(this::dfs);
			stack.push(vertex);
		}
		
		
		public Stack<Vertex> getStack(){
			return stack;
		}
		
	}
}
