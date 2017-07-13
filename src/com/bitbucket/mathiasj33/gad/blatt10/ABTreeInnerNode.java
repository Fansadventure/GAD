package com.bitbucket.mathiasj33.gad.blatt10;

import java.util.ArrayList;
import java.util.List;

public class ABTreeInnerNode extends ABTreeNode {
    private List<Integer> keys;
    private List<ABTreeNode> children;

    public ABTreeInnerNode(List<Integer> keys, List<ABTreeNode> children) {
        this.keys = keys;
        this.children = children;
    }

    public ABTreeInnerNode(int key, ABTreeNode left, ABTreeNode right) {
        keys = new ArrayList<Integer>();
        children = new ArrayList<ABTreeNode>();
        keys.add(key);
        children.add(left);
        children.add(right);
    }

    public ABTreeInnerNode(int key) {
        this(key, new ABTreeLeaf(), new ABTreeLeaf());
    }

    public int deg() {
        return children.size();
    }

    public List<ABTreeNode> split(int key) {
        int index = keys.indexOf(key);
        List<Integer> leftKeys = new ArrayList<>(keys.subList(0, index));
        List<Integer> rightKeys = new ArrayList<>(keys.subList(index + 1, keys.size()));
        List<ABTreeNode> leftChildren = new ArrayList<>(children.subList(0, index + 1));
        List<ABTreeNode> rightChildren = new ArrayList<>(children.subList(index + 1, children.size()));

        List<ABTreeNode> splitNodes = new ArrayList<>();
        splitNodes.add(new ABTreeInnerNode(leftKeys, leftChildren));
        splitNodes.add(new ABTreeInnerNode(rightKeys, rightChildren));
        return splitNodes;
    }

    public int getMiddleKey() {
        return keys.get((int) keys.size() / 2);
    }

    public List<ABTreeInnerNode> getPath(int key) {
        List<ABTreeInnerNode> path = new ArrayList<>();
        ABTreeInnerNode current = this;
        while (true) {
            path.add(current);
            if (current.children.stream().allMatch(c -> c instanceof ABTreeLeaf)) {
                return path;
            }
            boolean found = false;
            for (int i = 0; i < current.keys.size(); i++) {
                if (current.keys.get(i) >= key) {
                    current = (ABTreeInnerNode) current.children.get(i);
                    found = true;
                    break;
                }
            }
            if (!found)
                current = (ABTreeInnerNode) current.children.get(current.keys.size());
        }
    }

    @Override
    public void insert(int key) {
        children.add(new ABTreeLeaf());
        insertKeySorted(key);
    }

    @Override
    public boolean remove(int key) {
        if (!keys.contains(key))
            return false;
        children.remove(0);
        keys.remove(Integer.valueOf(key));
        return true;
    }

    public void removeKey(int key) {
        keys.remove(Integer.valueOf(key));
    }

    public int getKey(int index) {
        return keys.get(index);
    }

    public void removeLeaf() {
        if (children.stream().filter(c -> !(c instanceof ABTreeLeaf)).count() > 0)
            throw new IllegalStateException("Not a last level node");
        children.remove(0);
    }

    public void moveKey(int key, ABTreeInnerNode newOwner) {
        newOwner.insertKeySorted(key);
        keys.remove(Integer.valueOf(key));
    }

    public void moveMaxKey(ABTreeInnerNode newOwner) {
        moveKey(keys.get(keys.size() - 1), newOwner);
    }

    public void moveMinKey(ABTreeInnerNode newOwner) {
        moveKey(keys.get(0), newOwner);
    }

    public void addLeaf() {
        children.add(new ABTreeLeaf());
    }

    public void removeChild(ABTreeNode child) {
        children.remove(child);
    }

    public void addChild(int index, ABTreeNode child) {
        children.add(index, child);
    }

    public void addChild(ABTreeNode child) {
        children.add(child);
    }

    public void append(ABTreeInnerNode other) {
        keys.addAll(other.keys);
        children.addAll(other.children);
    }

    public int insertKeySorted(int key) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) > key) {
                keys.add(i, key);
                return i;
            }
        }
        keys.add(key);
        return keys.size() - 1;
    }

    public boolean containsKey(int key) {
        return keys.contains(key);
    }

    public int getChildIndex(ABTreeInnerNode child) {
        return children.indexOf(child);
    }

    public ABTreeNode getChild(int index) {
        return children.get(index);
    }

    private void addKey(int key) {
        keys.add(key);
    }

    public void swapBiggestKey(int key, ABTreeInnerNode newOwner) {
        if (!containsKey(key)) throw new IllegalArgumentException("This key is not in this node.");
        keys.remove(Integer.valueOf(key));
        int biggestKey = newOwner.keys.remove(newOwner.keys.size() - 1);
        newOwner.insertKeySorted(key);
        insertKeySorted(biggestKey);
    }

    @Override
    public boolean canSteal() {
        // TODO
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public boolean find(int key) {
        if (keys.contains(key))
            return true;
        if (children.stream().allMatch(c -> c instanceof ABTreeLeaf))
            return false;
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) >= key)
                return children.get(i).find(key);
        }
        return children.get(keys.size()).find(key);
    }

    @Override
    public int height() {
        if (children.size() == 0)
            throw new IllegalStateException("An inner node must have children");
        return 1 + children.stream().map(n -> n.height()).reduce(Integer::max).get();
    }

    @Override
    public Integer min() {
        if (children.stream().allMatch(c -> c instanceof ABTreeLeaf)) {
            return keys.stream().reduce(Integer::min).get();
        }
        return Math.min(keys.stream().reduce(Integer::min).get(),
                children.stream().filter(n -> !(n instanceof ABTreeLeaf)).map(n -> n.min()).reduce(Integer::min).get());
    }

    @Override
    public Integer max() {
        if (children.stream().allMatch(c -> c instanceof ABTreeLeaf)) {
            return keys.stream().reduce(Integer::max).get();
        }
        return Math.max(keys.stream().reduce(Integer::max).get(),
                children.stream().filter(n -> !(n instanceof ABTreeLeaf)).map(n -> n.max()).reduce(Integer::max).get());
    }

    @Override
    public boolean validAB(boolean root, int a, int b) {
        int firstHeight = children.get(0).height();
        for (ABTreeNode node : children) {
            if (node.height() != firstHeight)
                return false;
        }
        if (!root) {
            if (children.size() < a || children.size() > b)
                return false;
        }
        for (int i = 0; i < keys.size(); i++) {
            int key = keys.get(i);
            ABTreeNode left = children.get(i);
            ABTreeNode right = children.get(i + 1);
            if (left instanceof ABTreeLeaf || right instanceof ABTreeLeaf)
                continue;
            if (left.max() >= key || right.min() <= key)
                return false;
        }
        for (ABTreeNode node : children) {
            if (!node.validAB(false, a, b))
                return false;
        }
        return true;
    }

    @Override
    public int dot(StringBuilder sb, int from) {
        int mine = from++;
        sb.append(String.format("\tstruct%s [label=\"", mine));
        for (int i = 0; i < 2 * keys.size() + 1; i++) {
            if (i > 0)
                sb.append("|");
            sb.append(String.format("<f%d> ", i));
            if (i % 2 == 1)
                sb.append(keys.get(i / 2));
        }
        sb.append("\"];\n");
        for (int i = 0; i < children.size(); i++) {
            int field = 2 * i;
            sb.append(String.format("\tstruct%d:<f%d> -> struct%d;\n", mine, field, from));
            from = children.get(i).dot(sb, from);
        }
        return from;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((keys == null) ? 0 : keys.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ABTreeInnerNode other = (ABTreeInnerNode) obj;
        if (keys == null) {
            if (other.keys != null)
                return false;
        } else if (!keys.equals(other.keys))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return keys.toString();
    }
}
