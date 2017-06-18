package com.bitbucket.mathiasj33.gad.blatt05;

/**
 * Dieses Interface beschreibt eine Fabrik, die aus einer Größe ein
 * Hashable<K>-Objekt erzeugt.
 */
public interface HashableFactory<K> {
	DoubleHashable<K> create(int size);
}
