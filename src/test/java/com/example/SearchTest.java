package com.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import junit.framework.Assert;

public class SearchTest {
	

	@Test
	public void TestBinarySearch() {
		int[] num = { 1, 2, 3, 4, 5, 6, 7 };

		for (int i = 0; i < num.length; i++) {
			int foundIndex = binarySearch(num, num[i]);
			Assert.assertEquals(i, foundIndex);
		}

	}
	
	
	
	@Test
	public void firstRepeatingCharacter() {
		String input= "inside code";
		Map<Character, Long> reapeatCharacters=input.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()));

		System.out.println(reapeatCharacters);
	}

	private int binarySearch(int[] arr, int valToFind) {

		int start = 0;
		int end = arr.length;

		while (true) {
			int mid = (end - start) / 2 + start;
			if (arr[mid] == valToFind) {
				return mid;
			}
			if (arr[mid] > valToFind) {
				end = mid;
			} else if (arr[mid] < valToFind) {
				start = mid;
			}

		}

	}

	
}
