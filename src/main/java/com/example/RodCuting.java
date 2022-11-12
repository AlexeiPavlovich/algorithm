package com.example;

public class RodCuting {
	public int cut(int[] prices) {
		return lenghtPieces(prices, prices.length - 1);
	}

	private int lenghtPieces(int[] prices, int piecesInd) {
		int max = 0;
		for (int i = 0; i < piecesInd; i++) {
			max = Math.max(max, prices[piecesInd - i] + lenghtPieces(prices, i));
		}
		return max;
	}
}
