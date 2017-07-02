package com.bitbucket.mathiasj33.gad.blatt06;

import java.util.ArrayList;
import java.util.List;

public class NaturalMergesort implements SortingBase {
	
	private int[] result;
	
	public void sort(int[] numbers) {
		result = new int[numbers.length];
		List<Integer> indices = new ArrayList<>();
		for(int i = 1; i < numbers.length; i++) {
			if(numbers[i] < numbers[i-1]) indices.add(i);
		}
		for(int i = 0; i < indices.size(); i++) {
			int current = indices.get(i);
			int next = i == indices.size()-1 ? numbers.length : indices.get(i+1);
			merge(numbers, 0, current-1, next-1);
		}
	}

	public void merge(int[] numbers, int left, int middle, int right) {
		int j = left;
		int k = middle + 1;
		for (int i = 0; i <= right - left; i++) {
			if(j > middle) {result[i] = numbers[k]; k++;}
			else {
				if(k > right) {result[i] = numbers[j]; j++;}
				else {
					if(numbers[j] <= numbers[k]) {result[i] = numbers[j]; j++;}
					else {result[i] = numbers[k]; k++;}
				}
			}
		}

		for (int i = 0; i <= right - left; i++)
			numbers[left + i] = result[i];
	}
	
	private void mergeSort(int[] numbers, int left, int right) {
		if (left == right)
			return;
		int m = left+(right-left)/2;
		
		mergeSort(numbers, left, m);
		mergeSort(numbers, m + 1, right);
		
		int j = left;
		int k = m + 1;
		for (int i = 0; i <= right - left; i++) {
			if(j > m) {result[i] = numbers[k]; k++;}
			else {
				if(k > right) {result[i] = numbers[j]; j++;}
				else {
					if(numbers[j] <= numbers[k]) {result[i] = numbers[j]; j++;}
					else {result[i] = numbers[k]; k++;}
				}
			}
		}

		for (int i = 0; i <= right - left; i++)
			numbers[left + i] = result[i];
	}

	public String getName() {
		return "NaturalMergesort";
	}
}
