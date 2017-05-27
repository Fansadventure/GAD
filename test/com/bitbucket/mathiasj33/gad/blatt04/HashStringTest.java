package com.bitbucket.mathiasj33.gad.blatt04;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashStringTest {

	@Test
	public void testHash() throws Exception {
		HashString hs = new HashString(269);
		hs.setHashVector(new Vector(2,4,261,16));
		assertEquals(66, hs.hash(stringFromInts(11,7,4,3)));
		assertEquals(131, hs.hash(stringFromInts(5,11,24)));
	}
	
	@Test
	public void testRandomValueGeneration() {
		HashString hs = new HashString(269);
		hs.setHashVector(new Vector(1,2,3));
		hs.hash("abcd");
		Vector hashVector = hs.getHashVector();
		
		for(int i = 0; i < 100; i++) {
			HashString hs2 = new HashString(269);
			hs2.setHashVector(new Vector(1,2,3));
			hs2.hash("abcd");
			if(!hashVector.equals(hs2.getHashVector())) {
				assertEquals(hs.hash("abc"), hs2.hash("abc"));
				assertNotEquals(hs.hash("abcd"), hs2.hash("abcd"));
				return;
			}
		}
		fail("All the generated hashvectors were the same");
	}
	
	private String stringFromInts(int... ints) {
		String s = "";
		for(int i : ints) s += (char) i;
		return s;
	}
}
