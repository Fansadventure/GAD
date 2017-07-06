package com.bitbucket.mathiasj33.gad.blatt09;

import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Lesen Sie die Eingaben und fügen Sie sie der Reihe nach in Ihren AVL-Baum ein.
		AVLTree tree = new AVLTree();
//		tree.insertAll(10,5,15,1,7,11,16,3,6,8,9);
//		tree.insertAll(3,2,5,4,6,7);
//		tree.insertAll(8,9,5,6,4,3);
//		tree.insertAll(2,1,0,8,9,10,4,3,5,6);
//		tree.insertAll(2,1,0,8,9);
//		tree.insertAll(2,1,0);
//		tree.insertAll(2,1,0,-1,-2);
//		tree.insertAll(5,2,3,1,8,7,10,6,9,11,12);
		
		while (scanner.hasNextInt()) {
			int n = scanner.nextInt();
			tree.insert(n);
			System.out.println(tree.toString());
			if (!tree.validAVL()) {
				System.out.println(String.format("Baum ungueltig bei insert(%d)", n));
				System.out.println(tree.toString());
				return;
			}
		}

		// Wenn keine weitere Eingabe mehr erfolgt, geben Sie die Baumstruktur auf die
		// Standardausgabe aus. Sie sollten keine weitere Ausgabe tätigen, damit
		// TUMjudge Ihr Ergebnis korrekt erkennt. Debug-Ausgaben können Sie jederzeit
		// durch System.err.println(...) tätigen.
		System.out.println(tree.toString());
	}
}
