package com.bitbucket.mathiasj33.gad.blatt03;

import java.util.Arrays;

public class DynamicArray {
	private int[] elements;

	/**
	 * Diese Methode gibt die Größe des inneren Feldes zurück.
	 * 
	 * @return die Größe des inneren Feldes
	 */
	public int getInnerLength() {
		return elements.length;
	}

	private final int growthFactor;

	private final int maxOverhead;

	/**
	 * Diese Funktion passt den internen Speicher an veränderte Anforderungen
	 * bezüglich der Menge an gespeicherten Zahlenwerten an. Es soll dabei
	 * folgendes gelten:
	 * 
	 * - Wird aktuell mehr Speicher benötigt, als vorhanden ist (da die Funktion
	 * reallocate() privat ist, müssen Speicherforderung und Speicherbelegung
	 * bei ihrem Aufruf nicht zwangsläufig konsistent sein), wird die Größe des
	 * internen Speichers derart angepasst, dass er anschließend
	 * [Speicherforderung]*[Wachstumsfaktor] viele Elemente fassen kann.
	 * 
	 * - Ist die Speicherforderung zu gering, ist also der bereitgestellte
	 * Speicher mehr als um das [Maximaler Overhead]-fache größter als die
	 * Speicherforderung, wird die Größe des internen Speichers derart
	 * angepasst, dass er anschließend [Speicherforderung]*[Wachstumsfaktor]
	 * viele Elemente fassen kann.
	 * 
	 * @param usage
	 *            gibt an, welche Elemente des dynamischen Feldes aktuell in
	 *            Benutzung befindlich sind; es kann ein beliebiger Bereich in
	 *            Benutzung sein. Insbesondere kann es vorkommen, dass
	 *            usage.getFrom() größer ist als usage.getTo() - in diesem Fall
	 *            sind die Elemente [usage.getFrom(); elements.length - 1] und
	 *            [0; usage.getTo()] in Benutzung.
	 * 
	 * @param lengthNew
	 *            die neue Länge des in Benutzung befindlichen Feldbereiches
	 */
	private void reallocate(Interval usage, int newLength) {
		if(newLength > getInnerLength() || newLength * maxOverhead < getInnerLength()) {
			int newArrayLength = growthFactor * newLength;
			int[] newArray = new int[newArrayLength];
			copyToNewArray(usage, newArray);
			elements = newArray;
		}
	}
	
	private void copyToNewArray(Interval interval, int[] array) { //the used Elements are shifted to the beginning of the array
		if(interval instanceof EmptyInterval) return;
		int i = interval.getFrom();
		int counter = 0;
		while(counter < interval.getSize(getInnerLength())) {
			array[counter] = elements[i];
			i = (i+1) % getInnerLength();
			counter++;
		}
	}

	/**
	 * Dieser Konstruktor initialisiert ein dynamishes Feld. Es muss dabei
	 * gelten, dass
	 * 
	 * 1. growthFactor >= 1 2. maxOverhead >= 2 3. growthFactor < maxOverhead
	 * 
	 * @param growthFactor
	 *            der Wachstumsfaktor; um diesen wird der interne Speicher
	 *            vergrößtert, wenn nicht mehr genug Platz zur Verfügung steht.
	 * @param maxOverhead
	 *            der maximale Overhead; wird weniger als [maximaler
	 *            Overhead]-fache des genutzten Speichers benötigt, so wird der
	 *            interne Speicher verkleinert.
	 */
	public DynamicArray(int growthFactor, int maxOverhead) {
		if (growthFactor < 1 || maxOverhead < 2 || maxOverhead <= growthFactor)
			throw new RuntimeException("DynamicArray(): Invalid arguments");
		this.growthFactor = growthFactor;
		this.maxOverhead = maxOverhead;
		elements = new int[0];
	}

	/**
	 * Diese Methode erlaubt es dem Benutzer, das dynamische Feld über
	 * Änderungen im verwendeten Feldbereich zu informieren. Die Methode passt
	 * ggf. den internen Speicher an. Elemente dürfen dabei umkopiert und der
	 * verwendete Bereich verschoben werden. Die Methode gibt zurück, wo sich
	 * die Elemente, die sich vor dem Aufruf in Verwendung befanden, nach dem
	 * Aufruf befinden.
	 * 
	 * @param usage
	 *            gibt an, welche Elemente des dynamischen Feldes aktuell in
	 *            Benutzung befindlich sind; es kann ein beliebiger Bereich in
	 *            Benutzung sein. Insbesondere kann es vorkommen, dass
	 *            usage.getFrom() größer ist als usage.getTo() - in diesem Fall
	 *            sind die Elemente [usage.getFrom(); elements.length - 1] und
	 *            [0; usage.getTo()] in Benutzung.
	 * 
	 * @param minSize
	 *            gibt die minimale Größe benötigten Feldbereiches nach dem
	 *            Aufruf an; der Aufruf löscht niemals Elemente. Es gilt also,
	 *            dass nach dem Aufruf max(usage.getSize(), minSize) viel Platz
	 *            zur Verfügung steht.
	 * 
	 * @return ein neues Intervall, in dem sich alle Elemente in ungeänderter
	 *         Reihenfolge befinden, die vor dem Auruf in Verwendung waren
	 */
	public Interval reportUsage(Interval usage, int minSize) {
		int usageSize = usage.getSize(getInnerLength());
		int neededSize = max(usageSize, minSize);
		if(neededSize > getInnerLength() || neededSize * maxOverhead < getInnerLength()) {
			reallocate(usage, neededSize);
			if(usage instanceof EmptyInterval) return new EmptyInterval();
			return new NonEmptyInterval(0, usageSize - 1);
		}
		return usage;
	}

	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
	
	private int max(int a, int b) {
		return a >= b ? a : b;
	}
	
	/**
	 * Diese Methode holt ein Element aus dem dynamischen Feld.
	 * 
	 * @param index
	 *            der Index desjenigen Elementes, das ermittelt werden soll
	 * @return das ermittelte Element
	 */
	public int get(int index) {
		return elements[index];
	}

	/**
	 * Diese Methode setzt des Wert des dynamischen Feldes an einem bestimmten
	 * Index auf einen Wert.
	 * 
	 * @param index
	 *            der Index des zu setzenden Elementes
	 * @param value
	 *            der Wert des zu setzenden Elementes
	 */
	public void set(int index, int value) {
		elements[index] = value;
	}

}
