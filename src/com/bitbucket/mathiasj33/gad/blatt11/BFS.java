package com.bitbucket.mathiasj33.gad.blatt11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    private List<Integer> depths = new ArrayList<>();
    private List<Graph.Node> parents = new ArrayList<>();

    public BFS() {
        // TODO
        // Sie dürfen die Signatur dieser Funktion verändern
        // (z.B. im Parameter im Konstruktor zu übergeben),
        // müssen das dann aber in Graphilia.java entsprechend übernehmen.
    }

    /**
     * Führt eine Breitensuche vom Startknoten "start" aus, um das SSSP-Problem zu lösen.
     */
    public void sssp(Graph.Node start) {
        Queue<Graph.Node> queue = new LinkedList<>();
        queue.add(start);
        depths.add(start.getIndex(), 0);
        parents.add(start.getIndex(), null);
        while (!queue.isEmpty()) {
            Graph.Node current = queue.poll();
            current.getNeighbors().forEach(n -> {
                if (n.getIndex() >= parents.size() || parents.get(n.getIndex()) == null) {
                    queue.add(n);
                    depths.add(n.getIndex(), depths.get(current.getIndex()) + 1);
                    parents.add(n.getIndex(), current);
                }
            });
        }
    }

    /**
     * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion die Tiefe des Knotens n
     * vom Startknoten überprüft werden.
     */
    public Integer getDepth(Graph.Node n) {
        return depths.get(n.getIndex());
    }

    /**
     * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion der Vaterknoten
     * des Knotens n in Richtung Startknoten abgefragt werden.
     */
    public Graph.Node getParent(Graph.Node n) {
        return parents.get(n.getIndex());
    }
}
