package com.example;

import java.util.Arrays;

public class MergeSort {
	private int[] array;
	private int[] tempArray;

	public MergeSort(int[] array) {
		this.array = array;
		tempArray = new int[array.length];
	}

	public void sort() {
		mergeSort(0, array.length - 1);
		print();
	}

	private void mergeSort(int low, int hight) {
		if (low >= hight) {
			return;
		}
		int mid = (low + hight) / 2;
		mergeSort(low, mid);
		mergeSort(mid + 1, hight);
		mergeSubArrays(low, mid, hight);

	}

	private void mergeSubArrays(int low, int mid, int hight) {
		for (int i = low; i <= hight; i++) {
			tempArray[i] = array[i];
		}
		int i = low;
		int j = mid + 1;
		int k = low;
		while (i <= mid && j <= hight) {
			array[k++] = (tempArray[i] < tempArray[j]) ? tempArray[i++] : tempArray[j++];
		}

		while (i <= mid) {
			array[k++] = tempArray[i++];
		}
		while (j <= hight) {
			array[k++] = tempArray[j++];
		}

	}

	private void print() {
		System.out.println(Arrays.toString(array));
	}
}
