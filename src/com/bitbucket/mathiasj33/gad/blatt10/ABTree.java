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
	 * @param key
	 *            der Schlüssel, der gesucht werden soll
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		return root.find(key);
	}

	public void insertAll(int... keys) {
		for (int k : keys)
			insert(k);
	}

	/**
	 * Diese Methode fügt einen neuen Schlüssel in den (a,b)-Baum ein.
	 *
	 * @param key
	 *            der einzufügende Schlüssel
	 */
	public void insert(int key) {
		if (root == null) {
			root = new ABTreeInnerNode(key);
			return;
		}
		root.insert(key, a, b);
		if (root.deg() < a) {
			// TODO
		} else if (root.deg() > b) {
			int removedKey = root.getKeys().get((int) root.getKeys().size() / 2);
			List<ABTreeNode> children = root.split(removedKey);
			List<Integer> keys = new ArrayList<>();
			keys.add(removedKey);

			root = new ABTreeInnerNode(keys, children);
		}
	}

	/**
	 * Diese Methode löscht einen Schlüssel aus dem (a,b)-Baum.
	 *
	 * @param key
	 *            der zu löschende Schlüssel
	 * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false'
	 *         sonst
	 */
	public boolean remove(int key) {
		// TODO
		throw new RuntimeException("Not Implemented");
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
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
