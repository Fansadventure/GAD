package com.bitbucket.mathiasj33.gad.blatt05;

/**
 * Die Klasse {@link DoubleHashString} kann dazu verwendet werden, Strings zu
 * hashen.
 */
public class DoubleHashString extends DoubleHash<String> {

	/**
	 * Dieser Konstruktor initialisiert ein {@link DoubleHashString} Objekt für
	 * einen gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public DoubleHashString(int size) {
		super(size);
	}

	/**
	 * Diese Methode berechnet h(key) für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	public long hash(String key) {
		//Vereinfachung: Annahme: size = 2^16+n, so dass size prim ist. => size = 65537
		Vector keyVector = getKeyVector(key);
		if (keyVector.size() > hashVector.size()) {
			int neededValues = keyVector.size() - hashVector.size();
			generateNewVectorValues(neededValues, 65537);
		}
		Vector slicedVector = hashVector.slice(0, keyVector.size());
		int hash = slicedVector.scalarMult(keyVector) % size;
		return hash;
	}
	
	private Vector getKeyVector(String key) {
		Vector vector = new Vector();
		for(char c : key.toCharArray()) vector.addValue(c);
		return vector;
	}

	/**
	 * Diese Methode berechnet h'(key) für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	public long hashTick(String key) {
		int keySum = 0;
		for(char c : key.toCharArray()) keySum += c;
		return (keySum % (size - 1)) + 1; // siehe Folien
	}
}
