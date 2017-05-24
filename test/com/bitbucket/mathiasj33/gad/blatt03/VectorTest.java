package com.bitbucket.mathiasj33.gad.blatt03;

import org.junit.Test;
import static org.junit.Assert.*;

public class VectorTest {

	@Test
	public void testFromString() throws Exception {
		String s = "test";
		Vector v = Vector.fromString(s);
		for(int i = 0; i < s.length(); i++) {
			assertEquals(s.charAt(i), v.get(i));
		}
	}

	@Test
	public void testAddValue() throws Exception {
		Vector v = new Vector();
		v.addValue(5);
		v.addValue(7);
		assertEquals(5, v.get(0));
		assertEquals(7, v.get(1));
	}

	@Test
	public void testGetAndConstructor() throws Exception {
		Vector vector = new Vector(1,2,3);
		assertEquals(1, vector.get(0));
		assertEquals(2, vector.get(1));
		assertEquals(3, vector.get(2));
	}

	@Test
	public void testSet() throws Exception {
		Vector v = new Vector(1,2);
		v.set(0, 5);
		assertEquals(5, v.get(0));
	}

	@Test
	public void testSize() throws Exception {
		Vector v = new Vector(1,2,3,4,5,6,7);
		assertEquals(7, v.size());
	}

	@Test
	public void testSlice() throws Exception {
		Vector v = new Vector(1,2,3,4,5,6,7,8,9);
		Vector sliced = v.slice(0, 4);
		assertEquals(new Vector(1,2,3,4), sliced);
		sliced = v.slice(3,7);
		assertEquals(new Vector(4,5,6,7), sliced);
	}

	@Test
	public void testScalarMult() throws Exception {
		Vector v = new Vector(1,2,3,4);
		Vector v2 = new Vector(5,6,7,8);
		assertEquals(1*5+2*6+3*7+4*8, v.scalarMult(v2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testScalarMultExpectFailBecauseSize() {
		Vector v = new Vector(1,2,3,4);
		Vector v2 = new Vector(1,2,3);
		v.scalarMult(v2);
	}
}
