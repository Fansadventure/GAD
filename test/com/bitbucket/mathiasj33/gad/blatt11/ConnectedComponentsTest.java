package com.bitbucket.mathiasj33.gad.blatt11;

import com.bitbucket.mathiasj33.gad.blatt11.ConnectedComponents;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectedComponentsTest extends Setup {
    ConnectedComponents connectedComponents = new ConnectedComponents();

    @Test
    public void countConnectedComponents() throws Exception {
        int cc = connectedComponents.countConnectedComponents(g);
        assertEquals(2, cc);

        setup(numberNodes, edgesWithCircle);
        cc = connectedComponents.countConnectedComponents(g);
        assertEquals(2, cc);
    }

}