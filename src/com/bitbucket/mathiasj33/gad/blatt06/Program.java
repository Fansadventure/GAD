package com.bitbucket.mathiasj33.gad.blatt06;

import java.util.Random;
import java.util.Arrays;
import java.util.LinkedList;

public class Program {
	public static void main(String[] args) {
		// Anzahl der zu sortierenden Elemente
		//int n = 1000000;
		// int n = 100;
		//int n = 1000;
		int n = 10000;
		// int n = 100000;
		 //int n = 1000000;
		//int n = 10000000;

		// TODO: Zahlen für den Vergleich vorbereiten
		Random r = new Random();
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			// Mit zufälligen Zahlen initialisieren
			numbers[i] = r.nextInt(); //-> quicksort etwas schneller

			// Mit bereits sortierten Zahlen initialisieren
			//numbers[i] = i; //-> quicksort langsamer

			// Mit invers sortierten Zahlen initialisieren
			//numbers[i] = n-i; //-> quicksort langsamer
		}
		printArray("vorher: ", numbers);

		LinkedList<SortingBase> implementations = new LinkedList<SortingBase>();
		implementations.add(new Quicksort());
		implementations.add(new Mergesort());

		for (SortingBase sorter : implementations) {
			int[] numbersImpl = Arrays.copyOf(numbers, n);
			long startTime = System.currentTimeMillis();
			sorter.sort(numbersImpl);
			long estimatedTime = System.currentTimeMillis() - startTime;

			printArray(sorter.getName() + ": ", numbersImpl);
			if (estimatedTime > 0)
				System.out.println("Zeit: " + estimatedTime + "ms");
			else
				System.out.println("Zeit: <1ms");
			checkSorting(numbersImpl);
			System.out.println();
		}
	}

	public static void printArray(String prefix, int[] numbers) {
		int displayCount = numbers.length;
		String ext = "";
		if (numbers.length > 15) {
			displayCount = 15;
			ext = "...";
		}
		System.out.print(prefix);
		for (int i = 0; i < displayCount - 1; i++)
			System.out.print(numbers[i] + ", ");
		System.out.println(numbers[displayCount - 1] + ext);
	}

	public static void checkSorting(int[] numbers) {
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i - 1] > numbers[i])
				System.out.println("! FEHLER: " + numbers[i - 1] + " > " + numbers[i]);
		}
	}
}
