package com.bitbucket.mathiasj33.gad.blatt10;

public class ABTreeLeaf extends ABTreeNode {
	@Override
	public void insert(int key) {
		// TODO (???)
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public boolean canSteal() {
		// TODO
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public boolean find(int key) {
		// TODO
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public boolean remove(int key) {
		// TODO
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public Integer min() {
		// TODO
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public Integer max() {
		// TODO
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public boolean validAB(boolean root, int a, int b) {
		return true;
	}

	@Override
	public int dot(StringBuilder sb, int from) {
		sb.append(String.format("\tstruct%d [label=leaf, shape=ellipse];\n", from));
		return from + 1;
	}
}
