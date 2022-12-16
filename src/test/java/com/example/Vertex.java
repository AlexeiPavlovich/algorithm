package com.example;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private String name;
	
	private boolean visited;
	
	private List<Vertex> adjacencyList=new ArrayList<>();
	
	public Vertex(String name) {
		setName(name);
	}
	
	public void addNeighbor(Vertex neighbor) {
		adjacencyList.add(neighbor);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<Vertex> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(List<Vertex> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	
	@Override
	public String toString() {
		return getName();
	}


	
}
