package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class SortTest {

	@Test
	public void TestMerge() {
		int[] a = { 2, 8, 15, 18 };
		int[] b = { 5, 9, 12, 17 };
		int[] merge = new int[a.length + b.length];

		int mergeInd = 0;
		int aIndex = 0;
		int bIndex = 0;
		while (aIndex < a.length && bIndex < b.length) {

			merge[mergeInd++] = a[aIndex] < b[bIndex] ? a[aIndex++] : b[bIndex++];
		}
		if (aIndex < a.length) {
			System.arraycopy(a, aIndex, merge, mergeInd, a.length - aIndex);
		} else if (bIndex + 1 < b.length) {
			System.arraycopy(b, bIndex, merge, mergeInd, b.length - bIndex);
		}

		assertThat(merge).containsExactly(2, 5, 8, 9, 12, 15, 17, 18);
	}

	@Test
	public void TestQuickSort() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };

		quickSort(num, 0, num.length);
		assertThat(num).containsExactly(-22, -15, 1, 7, 20, 35, 55);
	}

	private void quickSort(int[] num, int left, int right) {
		if (left < right) {
			int partitopnIndex = partition(num, left, right);
			quickSort(num, left, partitopnIndex);
			quickSort(num, partitopnIndex + 1, right);
		}
	}

	private int partition(int[] num, int left, int right) {
		int pivot = num[left];
		int leftInd = left;
		int rightIndex = right;

		while (leftInd < rightIndex) {
			do {

				leftInd++;
			} while (num[leftInd] <= pivot && leftInd + 1 < right);

			do {
				rightIndex--;
			} while (num[rightIndex] >= pivot && rightIndex - 1 > left);

			if (leftInd < rightIndex) {
				swap(num, leftInd, rightIndex);
			}
		}
		swap(num, left, rightIndex);
		return rightIndex;
	}

	@Test
	public void TestBubbleSort() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };

		int unsortedPartitionIndex = num.length;

		while (unsortedPartitionIndex > 0) {
			for (int i = 0; i < unsortedPartitionIndex - 1; i++) {
				if (num[i] > num[i + 1]) {
					swap(num, i, i + 1);
				}
			}
			unsortedPartitionIndex--;
		}

		assertThat(num).containsExactly(-22, -15, 1, 7, 20, 35, 55);
	}

	@Test
	public void TestSelectionSort() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };
		int unsortedPartitionIndex = num.length;
		int largestInd;

		while (unsortedPartitionIndex > 0) {
			largestInd = 0;
			for (int i = 0; i < unsortedPartitionIndex; i++) {
				if (num[largestInd] < num[i]) {
					largestInd = i;
				}
			}
			swap(num, largestInd, unsortedPartitionIndex - 1);
			unsortedPartitionIndex--;
		}

		assertThat(num).containsExactly(-22, -15, 1, 7, 20, 35, 55);
	}

	@Test
	public void TestInsertionSort2() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };

		for (int unsortedPartitionIndex = 1; unsortedPartitionIndex < num.length; unsortedPartitionIndex++) {
			int elementToInsert = num[unsortedPartitionIndex];
			int i;
			for (i = unsortedPartitionIndex; i > 0 && num[i - 1] > elementToInsert; i--) {
				num[i] = num[i - 1];
			}
			num[i] = elementToInsert;
		}
		assertThat(num).containsExactly(-22, -15, 1, 7, 20, 35, 55);

	}

	@Test
	public void TestInsertionSort() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };
		int unsortedPartitionIndex = 1;
		int leftSideInd;

		while (unsortedPartitionIndex < num.length) {
			leftSideInd = unsortedPartitionIndex - 1;
			while (num[leftSideInd] > num[unsortedPartitionIndex] && leftSideInd > 0) {
				leftSideInd--;
			}
			if (num[leftSideInd] < num[unsortedPartitionIndex]) {
				leftSideInd++;
			}
			if (num[leftSideInd] > num[unsortedPartitionIndex]) {
				swapAndShift(num, leftSideInd, unsortedPartitionIndex);
			}
			unsortedPartitionIndex++;
		}

		assertThat(num).containsExactly(-22, -15, 1, 7, 20, 35, 55);

	}

	@Test
	public void TestMergeSort() {
		int[] num = { 20, 35, -15, 7, 55, 1, -22 };
		mergeSort(num, 0, num.length);
		assertThat(num).containsExactly(-22, -15, 1, 7, 20, 35, 55);

	}

	void mergeSort(int[] array, int start, int end) {
		if (end - start < 2) {
			return;
		}
		int mid = (start + end) / 2;
		mergeSort(array, start, mid);
		mergeSort(array, mid, end);
		// merge2(array, start, mid, end);
		merge(array, start, mid, end);
	}

	private void merge2(int[] num, int start, int mid, int end) {

		int lastElementLeftPartition = num[mid - 1];
		int firstElementRightPartition = num[mid];

		if (lastElementLeftPartition <= firstElementRightPartition) {
			return;
		}
		int i = start;
		int j = mid;
		int tempIndex = 0;
		int[] temp = new int[end - start];
		while (i < mid && j < end) {
			temp[tempIndex++] = num[i] <= num[j] ? num[i++] : num[j++];
		}
		System.arraycopy(num, i, num, start + tempIndex, mid - i);
		System.arraycopy(temp, 0, num, start, tempIndex);

	}

	private void merge(int[] num, int start, int mid, int end) {

		int mergeInd = 0;
		int aIndex = start;
		int bIndex = mid;
		int[] merge = new int[end - start];
		while (aIndex < mid && bIndex < end) {
			merge[mergeInd++] = num[aIndex] <= num[bIndex] ? num[aIndex++] : num[bIndex++];
		}

		if (aIndex < mid) {
			System.arraycopy(num, aIndex, num, start + mergeInd, mid - aIndex);
		} else if (bIndex < end) {
			System.arraycopy(num, bIndex, num, start + mergeInd, end - bIndex);
		}
		System.arraycopy(merge, 0, num, start, mergeInd);

	}

	private void swapAndShift(int[] array, int indTo, int indFrom) {
		int temp = array[indFrom];
		int save, saveBefore;
		saveBefore = array[indTo];
		for (int i = indTo; i < indFrom; i++) {
			save = array[i + 1];
			array[i + 1] = saveBefore;
			saveBefore = save;
		}
		array[indTo] = temp;
	}

	private void swap(int[] array, int i, int j) {
		int swap = array[i];
		array[i] = array[j];
		array[j] = swap;
	}

}
