package com.example;

import org.junit.Test;

public class TravellingSalesmanProblemTest {
	
	@Test
	public void TestTravellingSalesmanProblem() {
		int graph[][]= {
			/*    1  2  3  4*/
			/*1*/{ 0,10,15,20},
			/*2*/{10, 0,35,25},
			/*3*/{15,35, 0,30},
			/*4*/{20,25,30, 0},
		};
		
		TravellingSallesman travellingSallesman= new TravellingSallesman();
		
		System.out.println(travellingSallesman.calcMinDistance(graph));
	}

	private static class TravellingSallesman {

		public int calcMinDistance(int[][] graph) {
			boolean[] visited = new boolean[graph.length];
			int cost = 0;
			int countVisitedCities = 1;
			visited[0] = true;
			return calcMinDistanceRec(0, graph, visited, countVisitedCities, cost, Integer.MAX_VALUE);
			
		}
		

		private int calcMinDistanceRec(int currCityIndex, int[][] graph, boolean[] visited, int countVisitedCities, int cost,
				int calcMinDistance) {
			if (isAllCitiesVisited(countVisitedCities, graph) && hasLinkBetweenCities(graph, currCityIndex,0)) {
				//minimum distance included/excluded currCity
				return Math.min(calcMinDistance, cost + graph[currCityIndex][0]);
			}

			//iterate current city linked cities and calculate minimum distance to the src with included current linkedCity
			for (int indLinkedCities = 0; indLinkedCities < graph[currCityIndex].length; indLinkedCities++) {

				if (!visited[indLinkedCities] && hasLinkBetweenCities(graph, currCityIndex,indLinkedCities)) {
					visited[indLinkedCities] = true;
					calcMinDistance = calcMinDistanceRec(indLinkedCities, graph, visited, countVisitedCities + 1,
							cost + graph[currCityIndex][indLinkedCities], calcMinDistance);
					visited[indLinkedCities] = false;
				}

			}

			return calcMinDistance;
		}
		
		private boolean hasLinkBetweenCities(int[][] graph, int fromCityIndex,int toCityIndex) {
			return graph[fromCityIndex][toCityIndex] > 0;
		}
		
		private boolean isAllCitiesVisited(int countVisitedCities, int[][] graph) {
			return graph.length == countVisitedCities;
		}	

	}

}
