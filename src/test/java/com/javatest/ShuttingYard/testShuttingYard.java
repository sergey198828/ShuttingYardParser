package com.javatest.ShuttingYard;

import static org.junit.Assert.*;

import org.junit.*;

public class testShuttingYard {
	
	@Test
	public void test_postfix(){
		String expression = "2 + 2 * 3";
		String expectedOutput = "2 2 3 * + ";
		String postfix = com.javatest.ShuttingYard.ShunttingYard.postfix(expression);
		System.out.println(expectedOutput + " is " + postfix);
		assertEquals(expectedOutput, postfix);
	}
	
	@Test
	public void test_result(){
		String expression = "( 2 + 2 ) * 3";
		float expectedOutput = 12.0f;
		float result = com.javatest.ShuttingYard.ShunttingYard.result(expression);
		System.out.print(result);
		assertTrue(expectedOutput == result);
	}
}
