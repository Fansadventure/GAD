package com.bitbucket.mathiasj33.gad.blatt11;

import org.junit.Before;

public class Setup {
    static final int numberNodes = 5;
    static final int[][] edges = {{1,2}, {2,3}, {4,0}};
    static final int[][] edgesWithCircle = {{1,2}, {2,3}, {4,0}, {3,1}};
    static Graph g;
    static BFS search;

    @Before
    public void buildGraph() {
        setup(numberNodes,edges);
    }


    static void setup(int numberNodes, int[][] edges){
        g = new Graph();

        for (int i = 0; i < numberNodes; i++) {
            g.addNode();
        }

        for (int[] edge : edges) {
            g.addEdge(g.getNode(edge[0]), g.getNode(edge[1]));
        }

        search = new BFS(g.size());
    }
    static void search(int startIndex) {
        search.sssp(g.getNode(startIndex));
    }
}
