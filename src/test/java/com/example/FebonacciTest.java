package com.example;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FebonacciTest {
	
	@Test
	public void TestFebonacci() {
		long startTime = System.currentTimeMillis();
		System.out.println(fib(10));
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		
		startTime = System.currentTimeMillis();
		System.out.println(fib2(10000));
		endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		
		
		
		startTime = System.currentTimeMillis();
		System.out.println(fib3(1000000));
		endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
	}
	
	@Test
	public void TestFebonacciDynProg() {
		long startTime = System.currentTimeMillis();
		System.out.println(fibDynProg(1000,new HashMap<>()));
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
	}
	
	int fib(int n)
    {
        if (n <= 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }
	
	int fib3(int n) {
		int curr, prev, prev_prev;
		curr = 0;
		prev = 1;
		prev_prev = 0;
		for (int i = 2; i <= n; i++) {
			curr = prev + prev_prev;
			prev_prev = prev;
			prev = curr;

		}
		return curr;
	}
	
	
	int fib2(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
	
	int fibDynProg(int n, Map<Integer, Integer> saveNum) {
		if (n <= 1) {
			return n;
		}
		if (saveNum.containsKey(n)) {
			return saveNum.get(n);
		}
		int ans = fibDynProg(n - 1, saveNum) + fibDynProg(n - 2, saveNum);
		saveNum.put(n, ans);
		return ans;
	}
	
}
