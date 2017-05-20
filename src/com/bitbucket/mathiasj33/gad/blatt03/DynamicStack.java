package com.bitbucket.mathiasj33.gad.blatt03;

/**
 * Die Klasse DynamicStack soll einen Stapel auf Basis der Klasse
 * {@link DynamicArray} implementieren.
 */
public class DynamicStack {
	private DynamicArray dynArr;

	/**
	 * Dieses Feld speichert die Anzahl der Elemente auf dem Stapel.
	 */
	private int length;

	public int getLength() {
		return length;
	}

	public DynamicStack(int growthFactor, int maxOverhead) {
		dynArr = new DynamicArray(growthFactor, maxOverhead);
		length = 0;
	}

	/**
	 * Diese Methode legt ein Element auf den Stapel.
	 * 
	 * @param value
	 *            das Element, das auf den Stapel gelegt werden soll
	 */
	public void pushBack(int value) {
		Interval usage = length == 0 ? new EmptyInterval() : new NonEmptyInterval(0, length-1);
		length++;
		dynArr.reportUsage(usage, length);
		dynArr.set(length-1, value);
	}

	/**
	 * Diese Methode nimmt ein Element vom Stapel.
	 * 
	 * @return das entfernte Element
	 */
	public int popBack() {
		Interval usage = new NonEmptyInterval(0, length-1);
		length--;
		int value = dynArr.get(length);
		dynArr.reportUsage(usage, length);
		return value;
	}
}
