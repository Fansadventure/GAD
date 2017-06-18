package com.bitbucket.mathiasj33.gad.blatt05;

public class IntHashableFactory implements HashableFactory<Integer> {

	@Override
	public DoubleHashable<Integer> create(int size) {
		return new DoubleHashInt(size);
	}

}
