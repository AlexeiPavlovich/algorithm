package com.example;

import org.junit.Test;

public class MazeTest {
	
	
    private int[][] mazeTable= {
	  {1,1,1,1,1},
	  {0,0,1,0,1},
	  {1,1,1,0,1},
	  {0,0,0,0,1},
	  {1,1,1,0,1},
	};
    
    
	@Test
	public void TestMaze() {
		Maze maze=new Maze(mazeTable);
		maze.solve();
	}
}
