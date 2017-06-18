package com.bitbucket.mathiasj33.gad.blatt06;

public class Mergesort implements SortingBase {
	
	private int runs = 0;
	
	public void sort(int[] numbers) {
		mergeSort(numbers, 0, numbers.length - 1);
		System.out.println(runs);
	}

	private void mergeSort(int[] numbers, int left, int right) {
		if (left == right)
			return;
		int m = (left+right)/2;
		//long time = System.currentTimeMillis();
		mergeSort(numbers, left, m);
		mergeSort(numbers, m + 1, right);
		//System.out.println("MS took " + (System.currentTimeMillis() - time) + " ms.");
		int[] result = new int[numbers.length];
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
		return "Mergesort";
	}
	
	public static void main(String[] args) {
		int[] a = new int[]{8,9,8,7,6,5,4,3,2,1};
		new Mergesort().sort(a);
	}
}
