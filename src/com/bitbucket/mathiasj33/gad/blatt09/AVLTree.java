package com.bitbucket.mathiasj33.gad.blatt09;

/**
 * Diese Klasse implementiert einen AVL-Baum.
 */
public class AVLTree {
	/**
	 * Diese Variable speichert die Wurzel des Baumes.
	 */
	AVLTreeNode root = null;

	/**
	 * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich um einen
	 * validen AVL-Baum handelt. Dabei werden die folgenden Eigenschaften geprüft:
	 *
	 * - Die Höhe des linken Teilbaumes eines Knotens unterscheidet sich von der
	 * Höhe des rechten Teilbaumes um höchstens eins. - Die Schlüssel im linken
	 * Teilbaum eines Knotens sind kleiner als der oder gleich dem Schlüssel des
	 * Knotens. - Die Schlüssel im rechten Teilbaum eines Knotens sind größer als
	 * der Schlüssel des Knotens. - Die Balancierung jedes Knoten entspricht der
	 * Höhendifferenz der Teilbäume entsprechend der Erklärung in der Vorlesung.
	 *
	 * @return 'true' falls der Baum ein valider AVL-Baum ist, 'false' sonst
	 */
	public boolean validAVL() {
		return root.validAVL();
	}
	
	public void insertAll(int... keys) {
		for(int k : keys) insert(k);
	}
	
	/**
	 * Diese Methode fügt einen neuen Schlüssel in den AVL-Baum ein.
	 *
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {
		if(root == null) {
			root = new AVLTreeNode(key);
			return;
		}
		root = root.insert(key);
	}

	/**
	 * Diese Methode sucht einen Schlüssel im AVL-Baum.
	 *
	 * @param key der Schlüssel, der gesucht werden soll
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		return root.find(key);
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	public String dot() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph {\n");
		if (root != null)
			root.dot(sb);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	@Override
	public String toString() {
		return dot();
	}

}
