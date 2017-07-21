package com.bitbucket.mathiasj33.gad.blatt11;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BFSTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sssp() throws Exception {
        Graph graph = new Graph();
        for (int i = 0; i < 11; i++) {
            graph.addNode();
        }
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 3);
        addEdge(graph, 3, 10);
        addEdge(graph, 10, 7);
        addEdge(graph, 7, 8);
        addEdge(graph, 7, 9);
        addEdge(graph, 9, 6);
        addEdge(graph, 3, 4);
        addEdge(graph, 4, 1);
        addEdge(graph, 4, 7);
        addEdge(graph, 4, 5);
        addEdge(graph, 5, 6);
        addEdge(graph, 6, 7);
        addEdge(graph, 0, 2);
        addEdge(graph, 2, 5);
        addEdge(graph, 2, 6);

        BFS bfs = new BFS(graph.size());
        bfs.sssp(graph.getNode(0));
        for (int i = 0; i < 11; i++) {
            System.out.println("Node " + i + ". Depth: " + bfs.getDepth(graph.getNode(i)));
        }
        assertEquals(3, bfs.getParent(graph.getNode(10)).getIndex());
        assertEquals(7, bfs.getParent(graph.getNode(8)).getIndex());
        assertEquals(2, bfs.getParent(graph.getNode(6)).getIndex());
    }

    private void addEdge(Graph graph, int i, int j) {
        graph.addEdge(graph.getNode(i), graph.getNode(j));
    }

}