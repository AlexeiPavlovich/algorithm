package com.example;

import org.junit.Test;

public class MergeSortTest {
	@Test
	public void TestMergeSort() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22, -50, 188, -3, 0 };
		MergeSort sort = new MergeSort(num);
		sort.sort();
	}
}
