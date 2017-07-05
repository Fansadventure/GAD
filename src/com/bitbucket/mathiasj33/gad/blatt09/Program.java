package com.bitbucket.mathiasj33.gad.blatt09;

import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Lesen Sie die Eingaben und fügen Sie sie der Reihe nach in Ihren AVL-Baum ein.
		AVLTree tree = new AVLTree();
		while (scanner.hasNextInt()) {
			int n = scanner.nextInt();
			tree.insert(n);
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
