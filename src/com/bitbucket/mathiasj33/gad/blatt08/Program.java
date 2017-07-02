package com.bitbucket.mathiasj33.gad.blatt08;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		BinomialHeap bh = new BinomialHeap();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			bh.insert(scanner.nextInt());
		}

		try {
			while (true)
				System.out.println(bh.deleteMin());
		} catch (RuntimeException ex) {
			// heap is empty, just quit program
			return;
		}
	}

}
