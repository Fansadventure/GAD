/*
package com.bitbucket.mathiasj33.gad.blatt07;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RadixSortTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    public final RadixSort r = new RadixSort();

    @Test
    public void key() throws Exception {
        Assert.assertEquals(4, r.key(123456, 2));
        Assert.assertEquals(6, r.key(123456, 0));
        Assert.assertEquals(0, r.key(123456, 10));
    }

    @Test
    public void keyException() throws Exception {
        exception.expect(IllegalArgumentException.class);
        r.key(-23,2 );
    }

    @Test
    public void concatenate() throws Exception {
        final int digit = 1;
        ArrayList<Integer>[] buckets = new ArrayList[10];
        Integer[] elements = new Integer[4];

        buckets[4] = new ArrayList<>();
        buckets[4].add(140);
        buckets[4].add(45);

        buckets[0] =  new ArrayList<>();
        buckets[0].add(200);
        buckets[0].add(4);

        r.concatenate(buckets, elements);
        assertArrayEquals(new Integer[]{200, 4, 140, 45 }, elements);
    }

    @Test
    public void kSort() throws Exception {
        Integer[] elements0 = {2, 3, 1};
        r.kSort(elements0, 0);

        Integer[] sorted0 = {2, 3, 1};
        Arrays.sort(sorted0);

        assertArrayEquals(sorted0, elements0);


        Integer[] elements1 = {22, 51, 30, 201};
        r.kSort(elements1, 1);
        Integer[] sorted1 = {201, 22, 30, 51};

        assertArrayEquals(sorted1, elements1);
    }

    @Test
    public void sort() throws Exception {
        Integer[] elements = {2, 3, 1};
        r.sort(elements);
        assertArrayEquals(new Integer[]{1,2,3}, elements);
    }

}*/
