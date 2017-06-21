package com.bitbucket.mathiasj33.gad.blatt07;

import java.util.ArrayList;

public class RadixSort {
	public static int key(Integer element, int digit) throws IllegalArgumentException {
		if(element < 0) throw new IllegalArgumentException();
		int index = 0;
		while(index <= digit) {
			index++;
			element /= 10;
		}
		return element % 10;
	}

	private void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) {
		// TODO
	}
	// TODO
	private void kSort(Integer[] elements, int digit) {
		// TODO
	}

	public void sort(Integer[] elements) {
		// TODO
	}
	
	public static void main(String[] args) {
		System.out.println(key(512, 2));
	}
}
