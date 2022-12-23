package com.example;

import java.util.Arrays;

public class QuickSort {

	public void sort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(int[] array, int left, int right) {
		if (left < right) {
			int partitionInd = partition(array, left, right);
			quickSort(array, left, partitionInd - 1);
			quickSort(array, partitionInd + 1, right);
		}

	}

	private int partition(int[] array, int left, int right) {

		int pivot = array[right];

		int separateInd = left - 1;

		for (int currInd = left; currInd < right; currInd++) {
			if (array[currInd] < pivot) {
				swap(array, ++separateInd, currInd);
			}
		}

		swap(array, ++separateInd, right);
		return separateInd;

	}

	private void printArray(int[] array, int left, int right) {
		System.out.println(Arrays.toString(Arrays.copyOfRange(array, left, right + 1)));
	}

	private void swap(int[] array, int i, int j) {
		int swap = array[i];
		array[i] = array[j];
		array[j] = swap;
	}
}
