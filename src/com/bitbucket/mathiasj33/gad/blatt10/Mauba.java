package com.bitbucket.mathiasj33.gad.blatt10;

import java.util.Scanner;

public class Mauba {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ABTree abTree = new ABTree(2, 3);  //TODO: CHANGE!!!!!!! (3,5)
		abTree.insertAll(74,43,73,40,1,10,59,18,87);
//		while (scanner.hasNextInt()) {
//			int n = scanner.nextInt();
//			abTree.insert(n);
//			System.out.println(abTree.dot());
//			if (!abTree.validAB()) {
//				System.err.println(String.format("Baum ungueltig bei insert(%d)", n));
//				return;
//			}
//		}
//		scanner.close();

		System.out.println(abTree.dot());
	}
}
