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
		mergeSort(0, array.length);
		print();
	}

	void mergeSort(int low, int hight) {
		if (hight - low <= 1) {
			return;
		}
		int middle = (low + hight) / 2;
		mergeSort(low, middle);
		mergeSort(middle, hight);

		System.out.println("splited");
		for (int i = low; i < hight; i++) {
			System.out.print(" " + array[i]);
		}
		System.out.println();
		mergeSubArrays(low, middle, hight);
	}

	private void mergeSubArrays(int low, int middle, int hight) {
		System.out.println("Merge");
		for (int i = low; i < hight; i++) {
			System.out.print(" " + array[i]);
		}
		System.out.println();

		for (int i = low; i < hight; i++) {
			tempArray[i] = array[i];
		}

		int i = low;
		int j = middle;
		int k = low;

		System.out.println("left");
		for (int z = low; z < middle; z++) {
			System.out.print(" " + tempArray[z]);
		}
		System.out.println();

		System.out.println("right");
		for (int z = middle; z < hight; z++) {
			System.out.print(" " + tempArray[z]);
		}
		System.out.println();

		while (i < middle && j < hight) {
			array[k++] = tempArray[i] <= tempArray[j] ? tempArray[i++] : tempArray[j++];
		}

		while (i < middle) {
			array[k++] = tempArray[i++];
		}

		while (j < hight) {
			array[k++] = tempArray[j++];
		}
		System.out.println("merged");
		for (i = low; i < hight; i++) {
			System.out.print(" " + array[i]);
		}
		System.out.println();
		System.out.println("*******************");

	}

	private void print() {
		System.out.println(Arrays.toString(array));
	}
}
