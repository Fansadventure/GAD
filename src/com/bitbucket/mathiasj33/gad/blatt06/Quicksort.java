package com.bitbucket.mathiasj33.gad.blatt06;

public class Quicksort implements SortingBase {
	public void sort(int[] numbers) {
		quickSort(numbers, 0, numbers.length-1);
	}

	private void quickSort(int[] numbers, int left, int right) {
		if(left >= right) return;
		int pivot = numbers[right];
		int i = left-1; int j = right;
		do {
			do{ i++; } while(numbers[i] < pivot);
			do{ j--; } while(j >= left && numbers[j] > pivot);
			if(i < j) swap(numbers, i, j);
		} while(i < j);
		swap(numbers, i, right);
		quickSort(numbers, left, i-1);
		quickSort(numbers, i+1, right);
	}
	
	private void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}
	
	public String getName() {
		return "Quicksort";
	}
}
