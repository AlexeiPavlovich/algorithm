package com.example;

import java.util.Arrays;

import org.junit.Test;

public class QuickSortTest {
	@Test
	public void TestQuickSort() {
		int[] num = { -10, 20, 35, -15, 7, 90, 55, 1, -22, -50, 188, -3, 0 , 99};
		QuickSort sort = new QuickSort();
		sort.sort(num);
		System.out.println(Arrays.toString(num));
	}
}
