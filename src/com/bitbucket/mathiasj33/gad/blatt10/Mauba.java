package com.bitbucket.mathiasj33.gad.blatt10;

import java.util.Scanner;

public class Mauba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ABTree abTree = new ABTree(3, 5);
        //abTree.insertAll(33, 64, 30, 47, 21, 31, 58, 82, 54, 44, 74, 62, 28, 23, 42, 96, 76 , 20, 38);
        //System.out.println(abTree.dot());

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            System.err.println("Inserting" + n);
            abTree.insert(n);
            if (!abTree.validAB()) {
                System.err.println(String.format("Baum ungueltig bei insert(%d)", n));
                System.out.println(abTree.dot());
                return;
            }
        }
        scanner.close();

        System.out.println(abTree.dot());
    }
}
