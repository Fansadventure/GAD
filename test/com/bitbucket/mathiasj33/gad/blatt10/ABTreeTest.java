package com.bitbucket.mathiasj33.gad.blatt10;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 09.07.17.
 */
public class ABTreeTest {
    private static final int a = 3;
    private static final int b = 5;
    ABTree tree = new ABTree(a, b);

    private static int[] toInsert1 = {2, 3, 6, 5, 1};
    private static int[] toInsert2 = {-2, 4, 23, 1, 3, 0, 10, 11, 12, -3, -4};
    private static int[] toInsert3 = {109, 156, 49, 95, 120, 178, 23, 39, 71, 88, 99, 118, 130, 163, 172, 180, 194, 197};

    @Test
    public void validAB() throws Exception {
        ;
        for (Integer key : toInsert1) {
            tree.insert(key);
            tree.validAB();
        }
        clear();

        for (Integer key : toInsert2) {
            tree.insert(key);
            tree.validAB();
        }
        clear();

    }

    @Test
    public void find() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        for (Integer key : toInsert1) {
            tree.insert(key);
            assertTrue("Invalid tree by insert(" + key + ")\n" + tree, tree.validAB());
        }
        clear();
        for (Integer key : toInsert2) {
            tree.insert(key);
            assertTrue("Invalid tree by insert(" + key + ")\n" + tree, tree.validAB());
        }
        clear();
        for (Integer key : toInsert3) {
            tree.insert(key);
            assertTrue("Invalid tree by insert(" + key + ")\n" + tree, tree.validAB());
        }
        clear();
    }

    @Test
    public void remove() throws Exception {
        assertFalse(tree.remove(0));

        insert(toInsert1);
        assertFalse(tree.remove(0));
        boolean found;
        for (Integer key : toInsert1) {
            found = tree.remove(key);
            assertTrue(found);
            assertTrue("Invalid tree by remove(" + key + ")\n" + tree, tree.validAB());

        }

        insert(toInsert2);
        for (Integer key : toInsert2) {
            found = tree.remove(key);
            assertTrue(found);
            assertTrue("Invalid tree by remove(" + key + ")\n" + tree, tree.validAB());
        }

        insert(toInsert3);
        for (Integer key : toInsert3) {
            found = tree.remove(key);
            assertTrue(found);
            System.out.println("Removed " + key);
            System.out.println(tree.dot());
            assertTrue("Invalid tree by remove(" + key + ")\n" + tree, tree.validAB());
        }
    }

    private void insert(int[] toInsert) {
        clear();
        for (Integer key : toInsert)
            tree.insert(key);
    }

    private void clear() {
        tree = new ABTree(a, b);
    }
}