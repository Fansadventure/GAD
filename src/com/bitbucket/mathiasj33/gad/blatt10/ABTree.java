package com.bitbucket.mathiasj33.gad.blatt10;

import java.util.ArrayList;
import java.util.List;

public class ABTree {
    private int a;
    private int b;

    public ABTree(int a, int b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Diese Objektvariable speichert die Wurzel des Baumes.
     */
    private ABTreeInnerNode root = null;

    public boolean validAB() {
        return root.validAB(true, a, b);
    }

    /**
     * Diese Methode ermittelt die Höhe des Baumes.
     *
     * @return die ermittelte Höhe
     */
    public int height() {
        return root.height();
    }

    /**
     * Diese Methode sucht einen Schlüssel im (a,b)-Baum.
     *
     * @param key der Schlüssel, der gesucht werden soll
     * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
     */
    public boolean find(int key) {
        return root.find(key);
    }

    public void insertAll(int... keys) {
        for (int k : keys) {
            insert(k);
            System.out.println(validAB());
        }
    }

    /**
     * Diese Methode fügt einen neuen Schlüssel in den (a,b)-Baum ein.
     *
     * @param key der einzufügende Schlüssel
     */
    public void insert(int key) {
        if (root == null) {
            root = new ABTreeInnerNode(key);
            return;
        }

        List<ABTreeInnerNode> path = root.getPath(key);
        int lastIndex = path.size() - 1;
        path.get(lastIndex).insert(key);

        for (int i = lastIndex - 1; i >= 0; i--) {
            ABTreeInnerNode child = path.get(i + 1);
            if (child.deg() > b) {
                ABTreeInnerNode parent = path.get(i);
                makeValidAfterInsert(parent, child);
            }
        }

        if (root.deg() > b) {
            int removedKey = root.getMiddleKey();
            List<ABTreeNode> children = root.split(removedKey);
            List<Integer> keys = new ArrayList<>();
            keys.add(removedKey);
            root = new ABTreeInnerNode(keys, children);
        }
    }

    private void makeValidAfterInsert(ABTreeInnerNode parent, ABTreeInnerNode child) {
        int removedKey = child.getMiddleKey();
        parent.removeChild(child);
        List<ABTreeNode> children = child.split(removedKey);
        int insertIndex = parent.insertKeySorted(removedKey);

        parent.addChild(insertIndex, children.get(0));
        if (insertIndex + 1 >= parent.deg()) {
            parent.addChild(children.get(1));
        } else {
            parent.addChild(insertIndex + 1, children.get(1));
        }
    }

    /**
     * Diese Methode löscht einen Schlüssel aus dem (a,b)-Baum.
     *
     * @param key der zu löschende Schlüssel
     * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false'
     * sonst
     */
    public boolean remove(int key) {
        if (root == null || !root.find(key)) {
            return false;
        }
        List<ABTreeInnerNode> path = root.getPath(key);
        if (path.size() == 1) { //element is at root
            root.remove(key);
            return true;
        }
        ABTreeInnerNode node = path.get(path.size() - 1);
        node.removeLeaf();
        if (!node.containsKey(key)) {
            swapBiggestKey(key, path);
        }
        node.removeKey(key);

        if (node.deg() >= a) return true; //finished

        makeValidAfterRemove(path);

        return true;
    }

    private void makeValidAfterRemove(List<ABTreeInnerNode> path) {
        //check direct neighbors
        ABTreeInnerNode node = path.get(path.size() - 1);
        ABTreeInnerNode father = path.get(path.size() - 2);

        int index = father.getChildIndex(node);
        if (index > 0) {
            ABTreeInnerNode leftNeighbor = ((ABTreeInnerNode) father.getChild(index - 1));
            if (leftNeighbor.deg() > a) {
                leftNeighbor.removeLeaf();
                node.addLeaf();
                father.moveKey(father.getKey(index-1), node);  //TODO: das hier ist falsch
                leftNeighbor.moveMaxKey(father);
                return;
            }
        }
        if (index < father.deg() - 1) {
            ABTreeInnerNode rightNeighbor = ((ABTreeInnerNode) father.getChild(index+1));
            if (rightNeighbor.deg() > a) {
                rightNeighbor.removeLeaf();
                node.addLeaf();
                father.moveKey(father.getKey(index),node);
                rightNeighbor.moveMinKey(father);
                return;
            }
        }
        merge(path);
    }

    private void merge(List<ABTreeInnerNode> path) {
        for (int i = path.size() - 1; i >= 0; i--) {
            ABTreeInnerNode current = path.get(i);
            if (i == 0) { //root
                if (root.deg() < 2) {
                    root = (ABTreeInnerNode) root.getChild(0);
                }
                return;
            }
            ABTreeInnerNode father = path.get(i - 1);
            int index = father.getChildIndex(current);
            if (index > 0) {
                ABTreeInnerNode leftNeighbor = ((ABTreeInnerNode) father.getChild(index - 1));
                int keyToMove = father.getKey(index-1);
                father.moveKey(keyToMove, leftNeighbor);
                father.removeChild(current);
                leftNeighbor.append(current);
            } else {
                ABTreeInnerNode rightNeighbor = ((ABTreeInnerNode) father.getChild(index + 1));
                int keyToMove = father.getKey(index);
                father.moveKey(keyToMove, current);
                father.removeChild(rightNeighbor);
                current.append(rightNeighbor);
            }
        }
    }

    private void swapBiggestKey(int key, List<ABTreeInnerNode> path) {
        for (ABTreeInnerNode node : path) {
            if (node.containsKey(key)) {
                node.swapBiggestKey(key, path.get(path.size() - 1));
                return;
            }
        }
    }

    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     *
     * @return der Baum im Graphviz-Format
     */
    String dot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        sb.append("\tnode [shape=record];\n");
        if (root != null)
            root.dot(sb, 0);
        sb.append("}");
        return sb.toString();
    }
}
