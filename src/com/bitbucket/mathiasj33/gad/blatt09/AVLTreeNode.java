package com.bitbucket.mathiasj33.gad.blatt09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
		else if (left == null)
			return 1 + right.height();
		else if (right == null)
			return 1 + left.height();
		else
			return 1 + Math.max(left.height(), right.height());
	}

	public boolean validAVL() {
		if (isLeaf())
			return true;
		int diff = getBalance();
		if (Math.abs(diff) > 1)
			return false;
		if (balance != diff)
			return false;
		if (left != null) {
			int maxLeft = left.getKeyList().stream().reduce(Integer::max).get();
			if (maxLeft > key)
				return false;
			if (!left.validAVL())
				return false;
		}
		if (right != null) {
			int maxRight = right.getKeyList().stream().reduce(Integer::max).get();
			if (maxRight <= key)
				return false;
			if (!right.validAVL())
				return false;
		}
		return true;
	}

	private boolean isLeaf() {
		return left == null && right == null;
	}

	private List<Integer> getKeyList() {
		List<Integer> keys = new ArrayList<>();
		keys.add(key);
		if (isLeaf())
			return keys;
		if (left != null)
			keys.addAll(left.getKeyList());
		if (right != null)
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
		Stack<AVLTreeNode> path = getPathStack(key, null);
		AVLTreeNode newParent = path.peek();
		if (key <= newParent.key)
			newParent.left = new AVLTreeNode(key);
		else
			newParent.right = new AVLTreeNode(key);

		while (!path.isEmpty()) {
			path.pop().updateBalance();
		}
	}

	private void rotateLeft() {

	}

	private void rotateRight() {
	}
	
	public AVLTreeNode locate(int key) {
		return getPathStack(key, null).pop();
	}

	private void updateBalance() {
		balance = getBalance();
	}
	
	private int getBalance() {
		if(isLeaf()) return 0;
		if (right == null)
			return -left.height();
		else if (left == null)
			return right.height();
		return right.height() - left.height();
	}

	private Stack<AVLTreeNode> getPathStack(int key, Stack<AVLTreeNode> stack) {
		if (stack == null)
			stack = new Stack<AVLTreeNode>();
		stack.add(this);
		if (isLeaf())
			return stack;

		if (left == null) {
			if (key <= this.key)
				return stack;
			return right.getPathStack(key, stack);
		} else if (right == null) {
			if (key > this.key)
				return stack;
			return left.getPathStack(key, stack);
		}
		return key <= this.key ? left.getPathStack(key, stack) : right.getPathStack(key, stack);

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
