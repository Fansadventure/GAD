package com.bitbucket.mathiasj33.gad.blatt07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RadixchenTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		List<Integer> list = new ArrayList<>();
		while (scanner.hasNextInt()) {
			int value = scanner.nextInt();
			if(value < 0) {
				//System.out.println("Only positive values allowed.\n");
				continue;
			}
			list.add(value);
		}
		Integer[] array = list.toArray(new Integer[list.size()]);
		new RadixSort().sort(array);
		
		for(int i = 0; i < array.length; i++) System.out.println(array[i]);
	}
}
