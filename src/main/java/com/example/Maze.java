package com.example;

public class Maze {
	public Maze(int[][] maze) {
		super();
		this.maze = maze;
		this.solution = new int[maze.length][maze[0].length];
	}

	private int[][] maze;
	private int[][] solution;

	public void solve() {
		if (solveProblem(0, 0)) {
			System.out.println("SOLUTION");
			printSolution();
		} else {
			System.out.print("can't find way out of the maze!!!");
		}
	}

	private boolean solveProblem(int columIndex, int rowIndex) {
		if (foundWayOutOfTheMaze(columIndex, rowIndex)) {
			return true;
		}
		if (validateStep(columIndex, rowIndex)) {
			solution[columIndex][rowIndex] = 1;
			System.out.println("next step");
			printSolution();
			System.out.println();
			//System.out.println();
			if (solveProblem(columIndex + 1, rowIndex)) {
				return true;
			}
			if (solveProblem(columIndex, rowIndex + 1)) {
				return true;
			}
			if (solveProblem(columIndex, rowIndex - 1)) {
				return true;
			}
			if (solveProblem(columIndex - 1, rowIndex)) {
				return true;
			}
			System.out.println("Backtrack");
			solution[columIndex][rowIndex] = 0;

		}
		return false;
	}

	private boolean validateStep(int columIndex, int rowIndex) {
		if (columIndex < 0 || columIndex >= maze.length)
			return false;
		if (rowIndex < 0 || rowIndex >= maze[0].length)
			return false;
		return maze[columIndex][rowIndex] == 1 && solution[columIndex][rowIndex] == 0;
	}

	private boolean foundWayOutOfTheMaze(int columIndex, int rowIndex) {
		if (columIndex == maze.length - 1 && rowIndex == maze[0].length - 1) {
			solution[columIndex][rowIndex] = 1;
			return true;
		}
		return false;
	}

	private void printSolution() {
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution[i].length; j++) {
				System.out.print((solution[i][j] == 1) ? " * " : " 0 ");
			}
			System.out.println();
		}
	}

}
