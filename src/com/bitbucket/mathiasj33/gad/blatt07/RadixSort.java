package com.bitbucket.mathiasj33.gad.blatt07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {
	
	private ArrayList<Integer>[] buckets = new ArrayList[10];
	
	private int key(Integer element, int digit) throws IllegalArgumentException {
		if(element < 0 || digit < 0) throw new IllegalArgumentException();
		String stringRepresentation = element.toString();
		if(digit > stringRepresentation.length()) throw new IndexOutOfBoundsException();
		return Integer.parseInt(stringRepresentation.substring(digit, digit+1));
	}

	private void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) {
		int index = 0;
		for(List<Integer> list : buckets) {
			if(list == null) continue;
			for(int i : list) {
				elements[index] = i;
				index++;
			}
		}
	}
	
	private void kSort(Integer[] elements, int digit) {
		for(int i : elements) {
			System.out.println("Key of " + i + "; digit: " + digit);
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
			if((""+i).length() > max) max = (""+i).length();
		}
		return max;
	}
	
	public static void main(String[] args) {
		Integer[] a = new Integer[]{6,12,992,2421,23,5,67,23,9,0,1235,2};
		new RadixSort().sort(a);
		System.out.println(Arrays.toString(a));
	}
}
