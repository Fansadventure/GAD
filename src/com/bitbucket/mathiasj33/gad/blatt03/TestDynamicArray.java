package com.bitbucket.mathiasj33.gad.blatt03;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDynamicArray {

	@Test
	public void test() {
		DynamicArray arr = new DynamicArray(2, 3);
		arr.reportUsage(new EmptyInterval(), 1);
		arr.set(0, 2);
		assertEquals(2, arr.get(0));
		assertEquals(2, arr.getInnerLength());
		arr.reportUsage(new NonEmptyInterval(0,0), 5);
		assertEquals(10, arr.getInnerLength());
		arr.set(1, 1);
		assertEquals(1, arr.get(1));
		arr.set(2, 2);
		assertEquals(2, arr.get(2));
		arr.reportUsage(new NonEmptyInterval(0, 7), 7);
		assertEquals(10, arr.getInnerLength());
		arr.set(6, 6);
		arr.set(7, 7);
		//[2, 1, 2, 0, 0, 0, 6, 7, 0, 0]
		Interval i = arr.reportUsage(new NonEmptyInterval(7, 2), 11);
		assertEquals(0, i.getFrom());
		assertEquals(5, i.getTo());
		assertEquals("[7, 0, 0, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", arr.toString());
		arr.reportUsage(new NonEmptyInterval(0, 5), 6);
		System.out.println(arr);
	}

}
