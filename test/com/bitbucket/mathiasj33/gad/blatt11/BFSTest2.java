package com.bitbucket.mathiasj33.gad.blatt11;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BFSTest2 extends Setup {

    @Test
    public void sssp() throws Exception {
        search(0);
        search(3);
    }

    @Test
    public void getDepth() throws Exception {
        search(1);
        assertEquals(2,(int)search.getDepth(g.getNode(3)));
        try {
            search.getDepth(g.getNode(0));
            fail("This node should not be part of the bfs");
        } catch(IllegalArgumentException e) {}
        assertEquals(0,(int)search.getDepth(g.getNode(1)));

        setup(numberNodes, edgesWithCircle);
        search(1);
        try {
            search.getDepth(g.getNode(0));
            fail("This node should not be part of the bfs");
        } catch(IllegalArgumentException e) {}
        assertEquals(0,(int)search.getDepth(g.getNode(1)));
        assertEquals(1,(int)search.getDepth(g.getNode(3)));
    }

    @Ignore
    public void getParent() throws Exception {
    }
}