package ch8.ex03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GCDCalculatorTest {

	@Test
	public void test() {
		int a = -45;
		int b = 10;
		int actual1 = GCDCalculator.gcd(a, b);
		assertEquals(5, actual1);
		int actual2 = GCDCalculator.gcdFloorMod(a, b);
		assertEquals(5, actual2);
		int actual3 = GCDCalculator.gcdRem(a, b);
		assertEquals(5, actual3);
	}
}
