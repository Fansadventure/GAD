package com.bitbucket.mathiasj33.gad.blatt08;

import java.util.ArrayList;
import java.util.List;

public class BinomialTreeNode {
	
	private int key;
	private List<BinomialTreeNode> subTrees = new ArrayList<>();
	
	public BinomialTreeNode(int key) {
		this.key = key;
	}

	/**
	 * Ermittelt das minimale Element im Teilbaum.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		return key;
	}

	/**
	 * Gibt den Rang des Teilbaumes zurück.
	 *
	 * @return der Rang des Teilbaumes
	 */
	public int rank() {
		return subTrees.size();
	}

	/**
	 * Entfernt den minimalen Knoten (= Wurzel) und gibt eine Menge von
	 * Teilbäumen zurück, in die der aktuelle Baum zerfällt, wenn man
	 * den Knoten entfernt.
	 *
	 * @return die Menge von Teilbäumen
	 */
	public BinomialTreeNode[] deleteMin() {
		return subTrees.toArray(new BinomialTreeNode[subTrees.size()]);
	}

	public int getKey() {
		return key;
	}
	
	/**
	 * Diese Methode vereint zwei Bäume des gleichen Ranges.
	 *
	 * @param a der erste Baum
	 * @param b der zweite Baum
	 * @return denjenigen der beiden Bäume, an den der andere angehängt wurde
	 */
	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
		if(a == null) return b;
		else if(b == null) return a;
		
		if(a.getKey() < b.getKey()) {
			a.subTrees.add(b);
			return a;
		}
		else {
			b.subTrees.add(a);
			return b;
		}
	}
}
