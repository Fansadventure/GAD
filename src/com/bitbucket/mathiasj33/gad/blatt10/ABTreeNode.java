package com.bitbucket.mathiasj33.gad.blatt10;

public abstract class ABTreeNode {
	/**
	 * Diese Methode fügt einen Schlüssel in den Baum ein.
	 *
	 * @param key
	 *            der Schlüssel, der eingefügt wird
	 */
	public abstract void insert(int key, int a, int b);

	/**
	 * Diese Methode ermittelt, ob aus dem entsprechenden Knoten gestohlen
	 * werden kann oder nicht.
	 *
	 * @return 'true' falls gestohlen werden kann, 'false' sonst
	 */
	public abstract boolean canSteal();

	/**
	 * Diese Methode sucht den Schlüssel 'key' im Teilbaum.
	 *
	 * @param key
	 *            der Schlüssel, der gesucht wird
	 * @return 'true' falls der Schlüssel vorhanden ist, 'false' sonst
	 */
	public abstract boolean find(int key);
	
	/**
	 * Diese Methode entfernt den Schlüssel 'key' im Teilbaum, falls es ihn
	 * gibt.
	 *
	 * @param key
	 *            der Schlüssel, der entfernt werden soll
	 * @return 'true' falls der Schlüssel gefunden und entfernt wurde, 'false'
	 *         sonst
	 */
	public abstract boolean remove(int key);

	/**
	 * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
	 *
	 * @return die ermittelte Höhe
	 */
	public abstract int height();

	/**
	 * Diese Methode ermittelt das Minimum im jeweiligen Teilbaum.
	 * 
	 * @return das Minimum
	 */
	public abstract Integer min();

	/**
	 * Diese Methode ermittelt das Maximum im jeweiligen Teilbaum.
	 * 
	 * @return das Maximum
	 */
	public abstract Integer max();

	/**
	 * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich um einen
	 * validen (a,b)-Baum handelt.
	 *
	 * @return 'true' falls der Baum ein valider (a,b)-Baum ist, 'false' sonst
	 */
	public abstract boolean validAB(boolean root, int a, int b);

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der nächste freie Index für das Graphviz-Format
	 */
	public abstract int dot(StringBuilder sb, int from);
}