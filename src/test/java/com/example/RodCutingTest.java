package com.example;

import org.junit.Test;

public class RodCutingTest {
	@Test
	public void TestRodCuting() {
		int[] prices={0,1,3,5,6,7,9,10,11};
		RodCuting rodCuting=new RodCuting();
		System.out.println(rodCuting.cut(prices));
	}
}
