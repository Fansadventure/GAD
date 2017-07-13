/*
package com.bitbucket.mathiasj33.gad.blatt04;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class IntegrationTestAll {

	@Test
	public void testAll() throws Exception {
		startAllComponents();
		
		store("test", 42);
		assertReadEquals(42, "test");
		store("test", 38);
		assertReadEquals(38, "test");
		store("a", 1);
		store("b", 2);
		store("cac", 3);
		assertReadEquals(1, "a");
		assertReadEquals(2, "b");
		assertReadEquals(3, "cac");
		
		new Thread(() -> {
			try {
				Thread.sleep(100);
				store("1", 1);
				store("2", 2);
				store("3", 3);
				store("4", 4);
				store("5", 123);
				store("6", 5560);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
		new Thread(() -> {
			try {
				Thread.sleep(100);
				store("100", 100);
				store("101", 101);
				store("102", 102);
				store("103", 103);
				store("104", 104);
				store("105", 105);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
		Thread.sleep(500);
		assertReadEquals(1, "1");
		assertReadEquals(2, "2");
		assertReadEquals(3, "3");
		assertReadEquals(4, "4");
		assertReadEquals(123, "5");
		assertReadEquals(5560, "6");
		assertReadEquals(100, "100");
		assertReadEquals(101, "101");
		assertReadEquals(102, "102");
		assertReadEquals(103, "103");
		assertReadEquals(104, "104");
		assertReadEquals(105, "105");
		
		store("103", 10101023);
		assertReadEquals(10101023, "103");
	}
	
	private void store(String key, int value) throws Exception {
		Client.startClient("store " + key + " " + value, x -> {});
	}
	
	private void assertReadEquals(int expected, String key) throws Exception {
		Client.startClient("read " + key, x -> {
			assertEquals(Integer.valueOf(expected), x);
		});
	}
	
	private void startAllComponents() throws InterruptedException {
		new Thread(() -> {
			try {
				Master.main(new String[]{});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		Thread.sleep(50);
		
		for(int i = 0; i < 2; i++) {
			new Thread(() -> {
				try {
					Store.main(new String[]{});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
			Thread.sleep(50);
		}
	}
}
*/
