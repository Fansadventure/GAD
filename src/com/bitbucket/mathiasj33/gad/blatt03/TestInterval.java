package com.bitbucket.mathiasj33.gad.blatt03;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestInterval {

	@Test
	public void testGetSize() {
		Interval i = new NonEmptyInterval(0, 5);
		assertEquals(6, i.getSize(10));
		i = new NonEmptyInterval(1, 5);
		assertEquals(5, i.getSize(10));
		i = new NonEmptyInterval(4, 4);
		assertEquals(1, i.getSize(10));
		i = new NonEmptyInterval(7, 2);
		assertEquals(6, i.getSize(10));
		i = new NonEmptyInterval(7, 2);
		assertEquals(11, i.getSize(15));
	}

}
