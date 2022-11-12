package com.example;

import org.junit.Test;

public class QuickSortTest {
	@Test
	public void TestQuickSort() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22, -50, 188, -3, 0 };
		QuickSort sort = new QuickSort();
		sort.sort(num);
	}
}
