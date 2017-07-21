/*
package com.bitbucket.mathiasj33.gad.blatt05;

import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DoubleHashTableTest {

	@Mock
	private HashableFactory<Integer> factory = mock(HashableFactory.class);
	@Mock
	private DoubleHashInt dhi = mock(DoubleHashInt.class);
	
	@Test
	public void testInsert() throws Exception {
		
	}

	@Test
	public void testFind() throws Exception {

	}

	@Test
	public void testCollisions() throws Exception {
		when(factory.create(11)).thenReturn(dhi);
		DoubleHashTable<Integer, Integer> ht = new DoubleHashTable<>(11, factory);
		when(dhi.hashTick(2)).thenReturn(1L);
		when(dhi.hashTick(3)).thenReturn(2L);
		
		assertEquals(0, ht.collisions());
		assertEquals(0, ht.maxRehashes());
		when(dhi.hash(2)).thenReturn(5L);
		ht.insert(2, 2); //-> 5
		assertEquals(0, ht.collisions());
		assertEquals(1, ht.maxRehashes());
		when(dhi.hash(3)).thenReturn(5L);
		ht.insert(3, 3); //-> 5
		System.out.println(ht);
		assertEquals(1, ht.collisions());
		assertEquals(2, ht.maxRehashes());
		
		when(dhi.hash(4)).thenReturn(5L);
		when(dhi.hashTick(4)).thenReturn(2L);
		ht.insert(4, 4);
		System.out.println(ht);
		assertEquals(2, ht.collisions());
		assertEquals(3, ht.maxRehashes());
		
		when(dhi.hash(7)).thenReturn(0L);
		ht.insert(7, 7);
		assertEquals(2, ht.collisions());
		assertEquals(3, ht.maxRehashes());
		System.out.println(ht);
	}

	@Test
	public void testMaxRehashes() throws Exception {

	}

}
*/
