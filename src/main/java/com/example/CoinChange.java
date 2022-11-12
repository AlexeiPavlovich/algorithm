package com.example;

public class CoinChange {

	public int solve(int amount, int[] possible_coins) {
		int minCoins = coinChange(amount, possible_coins, " ");
		return (minCoins < Integer.MAX_VALUE - 1) ? minCoins : -1;
	}

	int coinChange(int amount, int[] possible_coins, String space) {
		// System.out.println("amount " + amount);
		if (amount == 0) {
			return 0;
		}

		int minCoins = Integer.MAX_VALUE - 1;
		for (int coin : possible_coins) {

			if (amount >= coin) {
				System.out.println(space + "coin :" + coin + " amount: " + amount);
				minCoins = Math.min(minCoins, coinChange(amount - coin, possible_coins, space + " ") + 1);
				System.out.println(space + "* min:" + minCoins);
			}
		}
		System.out.println(space + "return:" + minCoins);
		return minCoins;

	}

}
