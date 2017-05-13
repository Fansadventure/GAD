package com.bitbucket.mathiasj33.gad.blatt02;

import org.junit.Assert;
import org.junit.Test;

public class TestBinSea {

	@Test
	public void testSearch() {
		int[] array = {-10, 33, 50, 99, 123, 4242};
		System.out.println(BinSea.search(array, 99, true));
		System.out.println(BinSea.search(array, new NonEmptyInterval(80, 700)));

		int[] test = {-10, 33, 50, 99, 123, 4242};
		int lower = BinSea.search(test, 80, true);
		int upper = BinSea.search(test, 80, false);

		Assert.assertEquals(2, lower);
		Assert.assertEquals(3, upper);
	}

	@Test
	public void testSearchWithFound() {
		int[] test = {-10, 123, 4242};
		int lower = BinSea.search(test, 123, true);
		int upper = BinSea.search(test, 123, false);

		Assert.assertEquals(1, lower);
		Assert.assertEquals(1, upper);
	}

	@Test
	public void testSearchWithFound2() {
		int[] test = {-10, 10, 40, 123};
		int lower = BinSea.search(test, 123, true);
		int upper = BinSea.search(test, 123, false);

		Assert.assertEquals(3, lower);
		Assert.assertEquals(3, upper);
	}

	@Test
	public void testSearchWithFound3() {
		int[] test = {-10, 10, 40, 123};
		int lower = BinSea.search(test, -10, true);
		int upper = BinSea.search(test, -10, false);

		Assert.assertEquals(0, lower);
		Assert.assertEquals(0, upper);
	}

	@Test
	public void testSearchGivenBiggerNumberExpectNotFound() {
		int[] test = {-10, 33, 50, 99, 123, 4242};
		int lower = BinSea.search(test, 4300, true);
		int upper = BinSea.search(test, 4300, false);

		Assert.assertEquals(5, lower);
		Assert.assertEquals(-1, upper);
	}

	@Test
	public void testSearchGivenSmallerNumberExpectNotFound() {
		int[] test = {-10, 33, 50, 99, 123, 4242};
		int lower = BinSea.search(test, -100, true);
		int upper = BinSea.search(test, -100, false);

		Assert.assertEquals(-1, lower);
		Assert.assertEquals(0, upper);
	}

	@Test
	public void testIntervalSearch() {
		int[] test = {-10, 33, 50, 99, 123, 4242};
		Interval testInterval = new NonEmptyInterval(80, 700);
		Interval actual = BinSea.search(test, testInterval);
		Interval expected = new NonEmptyInterval(3, 4);

		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void testIntervalSearchExpectEmptyInterval() {
		int[] test = {-10, 33, 50, 99, 123, 4242};
		Interval testInterval = new NonEmptyInterval(4500, 5000);
		Interval actual = BinSea.search(test, testInterval);
		assert actual instanceof EmptyInterval;
		
		testInterval = new NonEmptyInterval(-100, -11);
		actual = BinSea.search(test, testInterval);
		assert actual instanceof EmptyInterval;
	}
}	
