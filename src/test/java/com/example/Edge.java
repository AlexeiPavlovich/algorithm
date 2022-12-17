package com.example;

public class Edge {
	private Vertex targetVertex;
	private int width;
	
	public Edge(Vertex targetVertex, int width) {
		setTargetVertex(targetVertex);
		setWidth(width);
	}

	public Vertex getTargetVertex() {
		return targetVertex;
	}

	public void setTargetVertex(Vertex targetVertex) {
		this.targetVertex = targetVertex;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


}
