package com.bitbucket.mathiasj33.gad.blatt06;

public class Mergesort implements SortingBase {
	
	private static int NUM_THREADS = 0;
	private int[] result;
	
	public void sort(int[] numbers) {
		result = new int[numbers.length];
		mergeSort(numbers, 0, numbers.length - 1);
	}

	private void mergeSort(int[] numbers, int left, int right) {
		if (left == right)
			return;
		int m = left+(right-left)/2;
		
		boolean threading = false;
		synchronized(this) {
			if(NUM_THREADS < 1) {
				threading = true;
				NUM_THREADS+=2;
			}
		}
		if(threading) {
			System.out.println("THREAD");
			Thread t1 = new Thread(() -> mergeSort(numbers, left, m));
			t1.start();
			mergeSort(numbers, m + 1, right);
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			mergeSort(numbers, left, m);
			mergeSort(numbers, m + 1, right);
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized(this){
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
			numbers[left + i] = result[i];}
	}

	public String getName() {
		return "Mergesort";
	}
}
