package com.bitbucket.mathiasj33.gad.blatt05;

import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleHashStringTest {

	@Test
	public void testHash() throws Exception {
		DoubleHashString dhs = new DoubleHashString(11);
		dhs.setHashVector(new Vector(5, 3, 7));
		assertEquals(2, dhs.hash("ahK")); //97, 104, 75
	}

}
