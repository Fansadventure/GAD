package com.bitbucket.mathiasj33.gad.blatt09;

import java.util.ArrayList;
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
		int diff = calculateBalance();
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
	public boolean find(int key) { // TODO: Das hier stimmt noch nicht ganz
		if (locate(key).key == key)
			return true;
		return false;
	}

	public AVLTreeNode insert(int key) {
		Stack<PathNode> path = getPathToLocate(key);
		AVLTreeNode newParent = path.peek().node;
		if (key <= newParent.key)
			newParent.left = new AVLTreeNode(key);
		else
			newParent.right = new AVLTreeNode(key);

		AVLTreeNode lastNode = null;
		while (!path.isEmpty()) {
			AVLTreeNode avlNode = path.pop().node;
			avlNode.updateBalance();
			if (avlNode.balance == 0) {
				lastNode = avlNode;
				return this;
			} else if (Math.abs(avlNode.balance) == 1) {
				lastNode = avlNode;
				continue;
			}

			int currentSignum = Integer.signum(avlNode.balance);
			int lastSignum = Integer.signum(lastNode.balance);
			if (currentSignum == lastSignum) {
				AVLTreeNode newTop = currentSignum == 1 ? avlNode.rotateLeft() : avlNode.rotateRight();
				return updateTopAfterRotate(newTop, path);
			} else {
				if (currentSignum == -1) {
					AVLTreeNode newTop = lastNode.rotateLeft();
					avlNode.left = newTop;
					newTop = avlNode.rotateRight();
					return updateTopAfterRotate(newTop, path);
				} else {
					AVLTreeNode newTop = lastNode.rotateRight();
					right = newTop;
					newTop = avlNode.rotateLeft();
					return updateTopAfterRotate(newTop, path);
				}
			}
		}
		return this;
	}

	private AVLTreeNode updateTopAfterRotate(AVLTreeNode newTop, Stack<PathNode> path) {
		if (!path.isEmpty()) {
			PathNode parent = path.pop();
			if (parent.parentDecision == Direction.RIGHT)
				parent.node.right = newTop;
			else
				parent.node.left = newTop;
			return this;
		} else {
			return newTop;
		}
	}

	private AVLTreeNode rotateLeft() {
		AVLTreeNode rightChild = right;
		right = rightChild.left;
		rightChild.left = this;
		rightChild.updateBalance();
		updateBalance();
		return rightChild;
	}

	private AVLTreeNode rotateRight() {
		AVLTreeNode leftChild = left;
		left = leftChild.right;
		leftChild.right = this;
		leftChild.updateBalance();
		updateBalance();
		return leftChild;
	}

	public AVLTreeNode locate(int key) {
		return getPathToLocate(key).pop().node;
	}

	private void updateBalance() {
		balance = calculateBalance();
	}

	private int calculateBalance() {
		if (isLeaf())
			return 0;
		if (right == null)
			return -1 - left.height();
		else if (left == null)
			return 1 + right.height();
		return right.height() - left.height();
	}

	private Stack<PathNode> getPathToLocate(int key) {
		Stack<PathNode> path = new Stack<>();

		AVLTreeNode current = this;
		while (true) {
			if (key <= current.key) {
				path.push(new PathNode(current, Direction.LEFT));
				if (current.left == null)
					return path;
				else
					current = current.left;
			} else if (key > current.key) {
				path.push(new PathNode(current, Direction.RIGHT));
				if (current.right == null)
					return path;
				else
					current = current.right;
			}
		}
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
