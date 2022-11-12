package com.example;

import java.util.Arrays;

public class QuickSort {

	public void sort(int[] array) {
		System.out.println("sort");
		System.out.println(Arrays.toString(array));
		System.out.println();
		quickSort(array, 0, array.length);
		System.out.println("result");
		System.out.println(Arrays.toString(array));
	}

	private void quickSort(int[] array, int left, int right) {
		if (left < right) {
			int partitionInd = partition(array, left, right);
			quickSort(array, left, partitionInd);
			quickSort(array, partitionInd + 1, right);
		}

	}

	
	
	
	private int partition(int[] array, int left, int right) {
		System.out.println("partition");
		printArray(array, left, right);
		int pivot = array[left];
		System.out.println("pivot: "+pivot);
		int leftPos = left;
		int rightPos = right;

		while (leftPos < rightPos) {
			do {
				leftPos++;
			} while (leftPos != right && array[leftPos] < pivot);

			do {
				rightPos--;
			} while (rightPos != left && array[rightPos] > pivot);

			if (leftPos < rightPos) {
				swap(array, leftPos, rightPos);
			}

		}
		swap(array, left, rightPos);
		System.out.println("after partition");
		printArray(array, left, right);
		System.out.println();
		return rightPos;

	}
	

	private void printArray(int[] array, int left, int right) {
		System.out.println(Arrays.toString(Arrays.copyOfRange(array, left, right)));
	}

	private void swap(int[] array, int i, int j) {
		int swap = array[i];
		array[i] = array[j];
		array[j] = swap;
	}
}
