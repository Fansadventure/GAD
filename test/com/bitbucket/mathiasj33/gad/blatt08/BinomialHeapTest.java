package com.bitbucket.mathiasj33.gad.blatt08;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	@Test
	public void test2() throws Exception {
		BinomialHeap heap = new BinomialHeap();
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(3);
		heap.insert(4);
		heap.insert(5);
		assertEquals(1, heap.deleteMin());
		assertEquals(2, heap.deleteMin());
		assertEquals(3, heap.deleteMin());
		assertEquals(3, heap.deleteMin());
		assertEquals(4, heap.deleteMin());
		assertEquals(5, heap.deleteMin());
	}
	
	@Test
	public void test3() throws Exception {
		Scanner s = new Scanner(new File("C:\\Users\\Mathias\\Documents\\GADHausaufgaben\\8\\test.txt"));
		ArrayList<Integer> list = new ArrayList<>();
		while (s.hasNext()){
		    list.add(Integer.parseInt(s.next()));
		}
		s.close();
		
		BinomialHeap heap = new BinomialHeap();
		for(int e : list) heap.insert(e);
		for(int e : list) {
			assertEquals(e, heap.deleteMin());
		}
	}
}
