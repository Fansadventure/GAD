package com.bitbucket.mathiasj33.gad.blatt03;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden, Strings zu hashen.
 */
public class HashString {

	private int size;
	private Vector hashVector = new Vector();

	/**
	 * Dieser Konstruktor initialisiert ein {@link HashString} Objekt für einen
	 * gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public HashString(int size) {
		this.size = size;
	}

	/**
	 * Diese Methode berechnet den Hashwert für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden sollen
	 * @return der Hashwert des Schlüssels
	 */
	public int hash(String key) {
		synchronized(this) {  //the possible vector modifications have to be thread safe
			if(key.length() > hashVector.size()) {
				int neededValues = key.length() - hashVector.size();
				generateNewVectorValues(neededValues);
			}
		}
		Vector keyVector = Vector.fromString(key);
		Vector slicedVector = hashVector.slice(0, keyVector.size());
		int hash = slicedVector.scalarMult(keyVector) % size;
		return hash;
	}
	
	public void setHashVector(Vector vector) {
		this.hashVector = vector;
	}
	
	public Vector getHashVector() {
		return hashVector;
	}
	
	private void generateNewVectorValues(int amount) {
		for(int i = 0; i < amount; i++) {
			int randomNumber = ThreadLocalRandom.current().nextInt(0, size);
			hashVector.addValue(randomNumber);
		}
	}
}
