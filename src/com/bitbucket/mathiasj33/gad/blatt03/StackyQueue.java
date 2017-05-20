package com.bitbucket.mathiasj33.gad.blatt03;

/**
 * Die Klasse StackyQueue soll eine Warteschlange auf Basis der Klasse
 * {@link DynamicStack} implementieren. Es soll ausschließlich die Klasse
 * {@link DynamicStack} zur Datenspeicherung verwendet werden.
 */
public class StackyQueue {
	private DynamicStack stack;
	private int growthFactor;
	private int maxOverhead;

	/**
	 * Diese Methode ermittelt die Länge der Warteschlange.
	 * 
	 * @return die Länge der Warteschlange
	 */
	public int getLength() {
		return stack.getLength();
	}

	/**
	 * Dieser Kontruktor initialisiert eine neue Schlange.
	 * 
	 * @param growthFactor
	 * @param maxOverhead
	 */
	public StackyQueue(int growthFactor, int maxOverhead) {
		this.growthFactor = growthFactor;
		this.maxOverhead = maxOverhead;
		stack = new DynamicStack(growthFactor, maxOverhead);
	}

	/**
	 * Diese Methode reiht ein Element in die Schlange ein.
	 * 
	 * @param value
	 *            der einzufügende Wert
	 */
	public void enqueue(int value) {
		stack.pushBack(value);
	}

	/**
	 * Diese Methode entfernt ein Element aus der Warteschlange.
	 * 
	 * @return das entfernte Element
	 */
	public int dequeue() {
		DynamicStack backup = new DynamicStack(growthFactor, maxOverhead);
		while(stack.getLength() > 1) backup.pushBack(stack.popBack());
		int value = stack.popBack();
		while(backup.getLength() > 0) stack.pushBack(backup.popBack());
		return value;
	}
}
