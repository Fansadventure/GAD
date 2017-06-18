package com.bitbucket.mathiasj33.gad.blatt05;

import java.util.Arrays;
import java.util.Optional;

/**
 * Die Klasse DoubleHashTable implementiert eine Hashtabelle, die doppeltes
 * Hashing verwendet.
 *
 * @param <K>
 *            der Typ der Schlüssel, die in der Hashtabelle gespeichert werden
 * @param <V>
 *            der Typ der Werte, die in der Hashtabelle gespeichert werden
 */
public class DoubleHashTable<K, V> {
	private DoubleHashable<K> hashFunctions;
	private int size;
	private Object[] pairs; // have to use Object[] because of type erasure
	private int maxRehashes = 0;

	/**
	 * Diese Methode implementiert h(x, i).
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @param i
	 *            der Index, der angibt, der wievielte Hash für den gegebenen
	 *            Schlüssel berechnet werden soll
	 * @return der generierte Hash
	 */
	public int hash(K key, int i) {
		return (int) ((hashFunctions.hash(key) + i*hashFunctions.hashTick(key)) % size);
	}

	/**
	 * Dieser Konstruktor initialisiert die Hashtabelle.
	 * 
	 * @param primeSize
	 *            die Größe 'm' der Hashtabelle; es kann davon ausgegangen
	 *            werden, dass es sich um eine Primzahl handelt.
	 * @param hashableFactory
	 *            Fabrik, die aus einer Größe ein DoubleHashable<K>-Objekt
	 *            erzeugt.
	 */
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		hashFunctions = hashableFactory.create(primeSize);
		this.size = primeSize;
		this.pairs = new Object[primeSize];
	}

	/**
	 * Diese Methode fügt entsprechend des doppelten Hashens ein Element in die
	 * Hashtabelle ein.
	 * 
	 * @param k
	 *            der Schlüssel des Elements, das eingefügt wird
	 * @param v
	 *            der Wert des Elements, das eingefügt wird
	 * @return 'true' falls das einfügen erfolgreich war, 'false' falls die
	 *         Hashtabelle voll ist.
	 */
	public boolean insert(K k, V v) {
		for (int i = 0; i < size; i++) {
			int index = hash(k, i);
			if(pairs[index] != null) {
				Pair<K, V> pair = (Pair<K, V>) pairs[index];
				if(pair._1.equals(k)) {
					if(i+1 > maxRehashes) maxRehashes = i+1;
					pairs[index] = new Pair<K,V>(k,v);
					return true;
				}
			}
			else if (pairs[index] == null) {
				if(i+1 > maxRehashes) maxRehashes = i+1;
				pairs[index] = new Pair<>(k,v);
				return true;
			}
		}
		return false;
	}

	/**
	 * Diese Methode sucht ein Element anhand seines Schlüssels in der
	 * Hashtabelle und gibt den zugehörigen Wert zurück, falls der Schlüssel
	 * gefunden wurde.
	 * 
	 * @param k
	 *            der Schlüssel des Elements, nach dem gesucht wird
	 * @return der Wert des zugehörigen Elements, sonfern es gefunden wurde
	 */
	public Optional<V> find(K k) {
		for(int i = 0; i < size; i++) {
			int index = hash(k, i);
			if(pairs[index] == null) return Optional.empty();
			Pair<K, V> pair = (Pair<K,V>) pairs[index];
			if(pair._1.equals(k)) return Optional.of(pair._2);
		}
		return Optional.empty();
	}

	/**
	 * Diese Methode ermittelt die Anzahl der Kollisionen, also die Anzahl der
	 * Elemente, nicht an der 'optimalen' Position in die Hashtabelle eingefügt
	 * werden konnten. Die optimale Position ist diejenige Position, die der
	 * erste Aufruf der Hashfunktion (i = 0) bestimmt.
	 * 
	 * @return die Anzahl der Kollisionen
	 */
	public int collisions() {
		int collisions = 0;
		for(int i = 0; i < pairs.length; i++) {
			Pair<K,V> pair = (Pair<K,V>) pairs[i];
			if(pair == null) continue;
			if(hash(pair._1, 0) != i) collisions++;
		}
		return collisions;
	}

	/**
	 * Diese Methode berechnet die maximale Anzahl von Aufrufen der
	 * Hashfunktion, die nötig waren, um ein einziges Element in die Hashtabelle
	 * einzufügen.
	 * 
	 * @return die berechnete Maximalzahl von Aufrufen
	 */
	public int maxRehashes() {
		return maxRehashes;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(pairs);
	}
}
