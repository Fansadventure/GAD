package com.bitbucket.mathiasj33.gad.blatt06;

import java.util.Arrays;

import org.junit.Test;

public class NaturalMergesortTest {
	@Test
	public void test() {
		new NaturalMergesort().sort(new int[]{5,31,64,11,10,75,111,8,82,99});
	}
	
	public void testMerge() {
		int[] a = {5,31,64,11};
		new NaturalMergesort().merge(a, 0, 3, 3);
		System.out.println(Arrays.toString(a));
	}
}
