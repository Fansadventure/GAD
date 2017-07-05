package com.bitbucket.mathiasj33.gad.blatt09;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse implementiert einen Knoten in einem AVL-Baum.
 */
public class AVLTreeNode {
	/**
	 * Diese Variable enthält den Schlüssel, den der Knoten speichert.
	 */
	private int key;

	/**
	 * Diese Variable speichert die Balancierung des Knotens - wie in der
	 * Vorlesung erklärt - ab. Ein Wert von -1 bedeutet, dass der linke Teilbaum
	 * um eins tiefer ist als der rechte Teilbaum. Ein Wert von 0 bedeutet, dass
	 * die beiden Teilbäume gleich hoch sind. Ein Wert von 1 bedeutet, dass der
	 * rechte Teilbaum tiefer ist.
	 */
	private int balance = 0;

	private AVLTreeNode left = null;
	private AVLTreeNode right = null;

	public AVLTreeNode(int key) {
		this.key = key;
	}

	/**
	 * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
	 *
	 * @return die ermittelte Höhe
	 */
	public int height() {
		if (isLeaf())
			return 0;
		else
			return 1 + Math.max(left.height(), right.height());
	}

	public boolean validAVL() {
		if (isLeaf())
			return true;
		int diff = right.height() - left.height();
		if (Math.abs(diff) > 1)
			return false;
		if (balance != diff)
			return false;
		int maxLeft = left.getKeyList().stream().reduce(Integer::max).get();
		if (maxLeft > key)
			return false;
		int maxRight = right.getKeyList().stream().reduce(Integer::max).get();
		if (maxRight <= key)
			return false;
		return left.validAVL() && right.validAVL();
	}

	private boolean isLeaf() {
		return left == null && right == null;
	}

	private List<Integer> getKeyList() {
		List<Integer> keys = new ArrayList<>();
		keys.add(key);
		if (isLeaf())
			return keys;
		keys.addAll(left.getKeyList());
		keys.addAll(right.getKeyList());
		return keys;
	}

	/**
	 * Diese Methode sucht einen Schlüssel im Baum.
	 *
	 * @param key
	 *            der zu suchende Schlüssel
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		if (locate(key).key == key)
			return true;
		return false;
	}

	public void insert(int key) {
		AVLTreeNode newParent = locate(key);
		if (key <= newParent.key)
			newParent.left = new AVLTreeNode(key);
		else
			newParent.right = new AVLTreeNode(key);

	}

	private void refreshBalance() {
		balance = right.height() - left.height();
	}

	private void rotateLeft() {

	}

	private void rotateRight() {

	}

	public AVLTreeNode getPathStack(int key) {
		TODO: hier weiter machen
	}
	
	public AVLTreeNode locate(int key) {
		if (isLeaf())
			return this;
		else
			return key <= this.key ? left.locate(key) : right.locate(key);
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @param sb
	 *            der StringBuilder für die Ausgabe
	 */
	public void dot(StringBuilder sb) {
		dotNode(sb, 0);
	}

	private int dotNode(StringBuilder sb, int idx) {
		sb.append(String.format("\t%d [label=\"%d, b=%d\"];\n", idx, key, balance));
		int next = idx + 1;
		if (left != null)
			next = left.dotLink(sb, idx, next, "l");
		if (right != null)
			next = right.dotLink(sb, idx, next, "r");
		return next;
	}

	private int dotLink(StringBuilder sb, int idx, int next, String label) {
		sb.append(String.format("\t%d -> %d [label=\"%s\"];\n", idx, next, label));
		return dotNode(sb, next);
	};
}
