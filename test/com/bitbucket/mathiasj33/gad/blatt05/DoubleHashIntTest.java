package com.bitbucket.mathiasj33.gad.blatt05;

import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleHashIntTest {
	
	@Test
	public void testHash() throws Exception {
		DoubleHashInt dhi = new DoubleHashInt(11);
		dhi.setHashVector(new Vector(1,2,3));
		assertEquals(5, dhi.hash(23)); //2,7
		assertEquals(9, dhi.hash(134)); //2,0,6
	}

	@Test
	public void testHashTick() throws Exception {
		
	}

}
