package com.bitbucket.mathiasj33.gad.blatt05;

import java.util.concurrent.ThreadLocalRandom;

public abstract class DoubleHash<T> implements DoubleHashable<T> {
	protected final int size;
	protected final int numBits;
	protected Vector hashVector;

	public DoubleHash(int size) {
		this.size = size;
		this.numBits = (int) (Math.log(size) / Math.log(2));
		this.hashVector = new Vector();
	}

	protected void generateNewVectorValues(int amount, int modSize) {
		for (int i = 0; i < amount; i++) {
			int randomNumber = ThreadLocalRandom.current().nextInt(0, modSize);
			hashVector.addValue(randomNumber);
		}
	}

	public void setHashVector(Vector v) {
		this.hashVector = v;
	}
}
