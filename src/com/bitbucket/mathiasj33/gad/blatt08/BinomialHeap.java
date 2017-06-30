package com.bitbucket.mathiasj33.gad.blatt08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinomialHeap {

	private List<BinomialTreeNode> trees = new ArrayList<>();
	private BinomialTreeNode min;
	
	public BinomialHeap() {
		
	}

	/**
	 * Diese Methode ermittelt das minimale Element im binomialen Haufen.
	 * Wenn es dieses nicht gibt (bei leerem Haufen), soll eine RuntimeException geworfen werden.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		if(min == null) throw new IllegalStateException("Heap is empty");
		return min.getKey();
	}

	/**
	 * Diese Methode fügt einen Schlüssel in den Haufen ein.
	 *
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {
		BinomialTreeNode node = new BinomialTreeNode(key);
		if(trees.isEmpty()) {
			trees.add(node);
			min = node;
			return;
		}
		merge(node);
	}
	
	private void merge(BinomialTreeNode node) {
		if(node.rank() >= trees.size()) {
			if(node.getKey() < min.getKey()) min = node;
			trees.add(node.rank(), node);
			return;
		}
		else if(trees.get(node.rank()) == null) {
			if(node.getKey() < min.getKey()) min = node;
			trees.set(node.rank(), node);
			return;
		}
		
		BinomialTreeNode tree = trees.get(node.rank());
		trees.set(node.rank(), null);
		tree = BinomialTreeNode.merge(tree, node);
		
		boolean foundPlace = false;
		for(int i = node.rank()+1; i < trees.size(); i++) {
			if(trees.get(i) == null) {
				trees.set(i, tree);
				foundPlace = true;
				break;
			}
			tree = BinomialTreeNode.merge(tree, trees.get(i));
			trees.set(i, null);
		}
		if(!foundPlace) {
			trees.add(tree);
		}
		if(tree.getKey() < min.getKey()) min = tree;
	}

	/**
	 * Diese Methode entfernt das minimale Element aus dem binomialen
	 * Haufen und gibt es zurück.
	 *
	 * @return das minimale Element
	 */
	public int deleteMin() {
		int minValue = min.getKey();
		trees.remove(min);
		for(BinomialTreeNode node : min.deleteMin()) {
			merge(node);
		}
		setPointerToNewMin();
		return minValue;
	}
	
	private void setPointerToNewMin() {
		if(trees.size() == 0) {
			min = null;
			return;
		}
		
		min = trees.stream().filter(o -> o != null).min((a,b) -> a.min() - b.min()).get();
	}
}
