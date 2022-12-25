package com.example;

import org.junit.Test;

import junit.framework.Assert;

public class SearchTest {

	@Test
	public void TestBinarySearch() {
		int[] num = { 1, 2, 3, 4, 5, 6, 7 };

		for (int i = 0; i < num.length; i++) {
			int foundIndex = binarySearch(num, 0, num.length, num[i]);
			System.out.println(foundIndex);
			Assert.assertEquals(num[i], foundIndex);
		}

	}

	private int binarySearch(int[] arr, int start, int end, int valToFind) {

		if (start > end) {
			return Integer.MAX_VALUE;
		}

		int mid = (start + end) / 2;

		int returnValue = mid;

		if (valToFind < mid) {
			returnValue = binarySearch(arr, start, mid - 1, valToFind);
		}
		if (valToFind > mid) {
			returnValue = binarySearch(arr, mid + 1, end, valToFind);
		}
		return returnValue;
	}

}
