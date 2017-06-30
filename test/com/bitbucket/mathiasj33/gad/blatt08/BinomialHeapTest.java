package com.bitbucket.mathiasj33.gad.blatt08;

import org.junit.Test;
import static org.junit.Assert.*;

public class BinomialHeapTest {

	@Test
	public void testMin() throws Exception {
	}

	@Test
	public void testInsert() throws Exception {
		BinomialHeap heap = new BinomialHeap();
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(4);
		heap.insert(-1);
		heap.insert(5);
		assertEquals(-1, heap.min());
		assertEquals(-1, heap.deleteMin());
		assertEquals(1, heap.deleteMin());
		
		heap.insert(-6);
		assertEquals(-6, heap.deleteMin());
		assertEquals(2, heap.min());
	}

	@Test
	public void testDeleteMin() throws Exception {
	}

}
