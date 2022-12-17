package com.example;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private String name;

	private boolean visited;

	private List<Vertex> adjacencyList = new ArrayList<>();

	private List<Edge> adjacencyEdgeList = new ArrayList<>();

	// shortest path from source to actual vertex
	private int minDistance = Integer.MAX_VALUE;

	// previuos node in the shorter path
	private Vertex predecessor;

	public Vertex(String name) {
		setName(name);
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
		return (predecessor != null) ? getName() + " predecessor: " + getPredecessor() : getName();
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	public List<Edge> getAdjacencyEdgeList() {
		return adjacencyEdgeList;
	}

	public void setAdjacencyEdgeList(List<Edge> adjacencyEdgeList) {
		this.adjacencyEdgeList = adjacencyEdgeList;
	}

}
