package com.bitbucket.mathiasj33.gad.blatt07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {
	
	public int key(Integer element, int digit) throws IllegalArgumentException {
		if(element < 0 || digit < 0) throw new IllegalArgumentException();
		String stringRepresentation = element.toString();
		if(digit >= stringRepresentation.length()) return 0;
		int index = stringRepresentation.length() - digit;
		return Integer.parseInt(stringRepresentation.substring(index-1, index));
	}

	public void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) {
		int index = 0;
		for(List<Integer> list : buckets) {
			if(list == null) continue;
			for(int i : list) {
				elements[index] = i;
				index++;
			}
		}
	}
	
	public void kSort(Integer[] elements, int digit) {
		ArrayList<Integer>[] buckets = new ArrayList[10];
		for(int i : elements) {
			int key = key(i, digit);
			if(buckets[key] == null) buckets[key] = new ArrayList();
			buckets[key].add(i);
		}
		concatenate(buckets, elements);
	}

	public void sort(Integer[] elements) {
		int maxDigits = findMaxAmountOfDigits(elements);
		for(int i = 0; i < maxDigits; i++) {
			kSort(elements, i);
		}
	}
	
	private int findMaxAmountOfDigits(Integer[] elements) {
		int max = Integer.MIN_VALUE;
		for(int i : elements) {
			int length = (""+i).length();
			if(length > max) max = length;
		}
		return max;
	}
}
