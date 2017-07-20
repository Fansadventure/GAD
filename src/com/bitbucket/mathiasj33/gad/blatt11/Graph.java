package com.bitbucket.mathiasj33.gad.blatt11;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes;

    public class Node {
        private List<Node> neighbors = new ArrayList<>();
        private int index;

        public Node(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public List<Node> getNeighbors() {
            return neighbors;
        }
    }

    public Graph() {
        nodes = new ArrayList<>();
    }

    /**
     * Erstellt einen neuen Knoten, und gibt den Index dieses Knotens zurück.
     * Der erste erstellte Knoten sollte Index 0 haben.
     * Knoten, die direkt nacheinander erstellt werden, sollten aufsteigende Indices haben.
     */
    public Integer addNode() {
        nodes.add(new Node(nodes.size()));
        return nodes.size() - 1;
    }

    /**
     * Liefert den Knoten zum angegebenen Index zurück
     */
    public Node getNode(Integer id) {
        return nodes.get(id);
    }

    /**
     * Fügt zwischen den beiden angegebenen Knoten eine (ungerichtete) Kante hinzu.
     */
    public void addEdge(Node a, Node b) {
        a.neighbors.add(b);
        b.neighbors.add(a);
    }
}
