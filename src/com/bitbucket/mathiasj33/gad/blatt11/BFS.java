package com.bitbucket.mathiasj33.gad.blatt11;

import java.util.*;

public class BFS {

    private int[] depths;
    private Graph.Node parents[];

    public BFS(int graphSize) {
        depths = new int[graphSize];
        Arrays.fill(depths, -1);
        parents = new Graph.Node[graphSize];
    }

    /**
     * Führt eine Breitensuche vom Startknoten "start" aus, um das SSSP-Problem zu lösen.
     */
    public void sssp(Graph.Node start) {
        Queue<Graph.Node> queue = new LinkedList<>();  //würde man hier einen Stack anstatt einer Queue verwenden, hätte man eine Tiefensuche
        queue.add(start);
        depths[start.getIndex()] = 0;
        parents[start.getIndex()] = null;
        while (!queue.isEmpty()) {
            Graph.Node current = queue.poll();
            current.getNeighbors().forEach(n -> {
                if (n.getIndex() != start.getIndex() && parents[n.getIndex()] == null) {
                    queue.add(n);
                    depths[n.getIndex()] = depths[current.getIndex()] + 1;
                    parents[n.getIndex()] = current;
                }
            });
        }
    }

    /**
     * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion die Tiefe des Knotens n
     * vom Startknoten überprüft werden.
     */
    public Integer getDepth(Graph.Node n) {
        if (depths[n.getIndex()] == -1) throw new IllegalArgumentException("This node is not path of the bfs");
        return depths[n.getIndex()];
    }

    /**
     * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion der Vaterknoten
     * des Knotens n in Richtung Startknoten abgefragt werden.
     */
    public Graph.Node getParent(Graph.Node n) {
        if (parents[n.getIndex()] == null) throw new IllegalArgumentException("This node is not path of the bfs");
        return parents[n.getIndex()];
    }

    public List<Integer> getFoundNodeIndices() {
        List<Integer> foundNodeIndices = new ArrayList<>();
        for (int i = 0; i < depths.length; i++) {
            if(depths[i] != -1) foundNodeIndices.add(i);
        }
        return foundNodeIndices;
    }
}
