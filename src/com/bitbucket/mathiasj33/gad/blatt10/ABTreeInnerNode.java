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

	public List<Integer> getKeys() {
		return keys;
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

	String dotTree() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph {\n");
		sb.append("\tnode [shape=record];\n");
		if (this != null)
			this.dot(sb, 0);
		sb.append("}");
		return sb.toString();
	}

	@Override
	public void insert(int key, int a, int b) {
		if (children.stream().allMatch(c -> c instanceof ABTreeLeaf)) {
			children.add(new ABTreeLeaf());
			insertKeySorted(key);
			return;
		}
		for (int i = 0; i < keys.size(); i++) {
			if (keys.get(i) >= key) {
				ABTreeNode subTree = children.get(i);
				subTree.insert(key, a, b);
				makeSuccessorValid(i, a, b);
				return;
			}
		}
		children.get(keys.size()).insert(key, a, b);
		makeSuccessorValid(keys.size(), a, b);
	}

	private void makeSuccessorValid(int index, int a, int b) {
		ABTreeNode successor = children.get(index);
		if (successor instanceof ABTreeLeaf)
			throw new IllegalStateException("Cannot make leaf valid");
		ABTreeInnerNode inner = (ABTreeInnerNode) successor;
		if (inner.deg() > b) {
			int removedKey = inner.getKeys().get((int) inner.getKeys().size() / 2);
			this.children.remove(successor);
			List<ABTreeNode> children = inner.split(removedKey);
			int insertIndex = insertKeySorted(removedKey);

			this.children.add(insertIndex, children.get(0));
			if (insertIndex + 1 >= children.size()) {
				this.children.add(children.get(1));
			} else {
				this.children.add(insertIndex + 1, children.get(1));
			}
		}
	}

	private int insertKeySorted(int key) {
		for (int i = 0; i < keys.size(); i++) {
			if (keys.get(i) > key) {
				keys.add(i, key);
				return i;
			}
		}
		keys.add(key);
		return keys.size() - 1;
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

	public boolean remove(int key) {
		// TODO
		throw new RuntimeException("Not Implemented");
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
}
