package com.bitbucket.mathiasj33.gad.blatt03;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden, Strings zu hashen.
 */
public class HashString {

	private int size;
	private Vector vector = new Vector();

	/**
	 * Dieser Konstruktor initialisiert ein {@link HashString} Objekt für einen
	 * gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public HashString(int size) {
		this.size = size;
		vector = new Vector(2,4,261,16);
	}

	/**
	 * Diese Methode berechnet den Hashwert für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden sollen
	 * @return der Hashwert des Schlüssels
	 */
	public int hash(String key) {
		if(key.length() < vector.size()) {
			int neededValues = key.length() - vector.size();
			generateNewVectorValues(neededValues);
		}
		Vector keyVector = Vector.fromString(key);
		Vector slicedVector = vector.slice(0, keyVector.size());
		int hash = vector.scalarMult(keyVector) % size;
		return hash;
	}
	
	private void generateNewVectorValues(int amount) {
		
	}
}
