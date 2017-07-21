package com.bitbucket.mathiasj33.gad.blatt11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConnectedComponents {

    /**
     * Zählt alle Zusammenhangskomponenten im gegebenen Graphen g
     */
    public int countConnectedComponents(Graph g) {
        int numConnectedComponenets = 0;
        List<Graph.Node> foundNodes = new ArrayList<>();
        for (int i = 0; i < g.size(); i++) {
            Graph.Node n = g.getNode(i);
            if (!foundNodes.contains(n)) {
                numConnectedComponenets++;
                BFS bfs = new BFS(g.size());
                bfs.sssp(n);
                foundNodes.addAll(bfs.getFoundNodeIndices().stream()
                        .map(idx -> g.getNode(idx)).collect(Collectors.toList()));
            }
        }
        return numConnectedComponenets;
    }
}