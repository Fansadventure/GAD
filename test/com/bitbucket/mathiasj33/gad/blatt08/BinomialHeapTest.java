package com.bitbucket.mathiasj33.gad.blatt08;

import org.junit.Test;
import static org.junit.Assert.*;

public class BinomialHeapTest {

	@Test
	public void test() throws Exception {
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
		
		heap = new BinomialHeap();
		for(int i = 0; i < 1000; i++) {
			heap.insert(i);
		}
		for(int i = 0; i < 1000; i++) {
			assertEquals(i, heap.deleteMin());
		}
	}
}
