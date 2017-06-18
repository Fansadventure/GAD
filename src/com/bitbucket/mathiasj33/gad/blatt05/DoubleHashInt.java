package com.bitbucket.mathiasj33.gad.blatt05;

/**
 * Die Klasse {@link DoubleHashInt} kann dazu verwendet werden, Integer zu
 * hashen.
 */
public class DoubleHashInt extends DoubleHash<Integer> {

	public DoubleHashInt(int size) {
		super(size);
	}

	/**
	 * Diese Methode berechnet h(key) für einen Integer.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	@Override
	public long hash(Integer key) {
		Vector keyVector = getKeyVector(key);
		if (keyVector.size() > hashVector.size()) {
			int neededValues = keyVector.size() - hashVector.size();
			generateNewVectorValues(neededValues, size);
		}
		Vector slicedVector = hashVector.slice(0, keyVector.size());
		int hash = slicedVector.scalarMult(keyVector) % size;
		return hash;
	}

	private Vector getKeyVector(Integer key) {
		String bitString = Integer.toBinaryString(key);
		String[] bitParts = getParts(bitString);
		Vector keyVector = new Vector();
		for (String s : bitParts) {
			keyVector.addValue(Integer.parseInt(s, 2));
		}
		return keyVector;
	}

	private String[] getParts(String bitString) {
		bitString = extendBitString(bitString);

		String[] parts = new String[bitString.length() / numBits];
		String currentBits = "";
		for (int i = 0; i < bitString.length(); i++) {
			currentBits += bitString.charAt(i);
			if ((i + 1) % numBits == 0) {
				parts[(i + 1) / numBits - 1] = currentBits;
				currentBits = "";
			}
		}

		return parts;
	}

	private String extendBitString(String bitString) {
		while (bitString.length() % numBits != 0) {
			bitString = "0" + bitString;
		}
		return bitString;
	}

	/**
	 * Diese Methode berechnet h'(key) für einen Integer.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	@Override
	public long hashTick(Integer key) {
		return (key % (size - 1)) + 1; // siehe Folien
	}
}
