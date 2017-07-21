package com.bitbucket.mathiasj33.gad.blatt11;

import java.util.ArrayList;
import java.util.Scanner;

public class Graphilia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> tests = new ArrayList<Integer>();

        Graph g = new Graph();

        int n = scanner.nextInt(); // Zeile 1: wie viele Knoten sind vorhanden?
        for (int i = 0; i < n; i++) {
            g.addNode(); // Bereite n Knoten mit aufsteigenden Indices vor
        }
        for (int i = 0; i < n; i++) {
            // nächste n Zeilen: erste Zahl e = Anzahl der Kanten...
            Graph.Node node = g.getNode(i);
            int e = scanner.nextInt();
            for (int j = 0; j < e; j++) {
                // ...jede folgende Zahl entspricht dem Knotenindex, zu dem eine Kante existiert
                Graph.Node neighbor = g.getNode(scanner.nextInt());
                g.addEdge(node, neighbor);
            }
        }

        int testCount = scanner.nextInt(); // Zeile n+2: wie viele Knoten sollen getestet werden?
        for (int i = 0; i < testCount; i++) {
            // Für jeden Testfall eine Zahl = Index des Knotens, dessen Tiefe später ausgegeben werden soll
            tests.add(scanner.nextInt());
        }
        scanner.close();

        // Aufgabe: Führe eine Breitensuche auf dem ersten Knoten (Index 0) aus,
        // und gebe die daraus resultierende Knotentiefe der gelisteten Testknoten aus.
        // Zähle dann die Zusammenhangskomponenten, und gebe Sie in der angegebenen
        // Formatierung aus.

        BFS search = new BFS(g.size());
        search.sssp(g.getNode(0));

        for (Integer t : tests) {
            Integer depth = search.getDepth(g.getNode(t));
            System.out.println(depth);
        }

        ConnectedComponents cc = new ConnectedComponents();
        System.out.println("Connected components: " + cc.countConnectedComponents(g));
    }
}
