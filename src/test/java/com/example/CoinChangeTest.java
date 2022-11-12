package com.example;

import org.junit.Test;

public class CoinChangeTest {
	@Test
	public void TestCoinChange() {
		int[] possible_coins= {3,2};
		CoinChange coinChange =new CoinChange();
		System.out.println("solved:"+coinChange.solve(5,possible_coins));
		
		
		int[] possible_coins1= {2,3,7};
		System.out.println("solved:"+coinChange.solve(15,possible_coins1));
		
		
	}
	
}
