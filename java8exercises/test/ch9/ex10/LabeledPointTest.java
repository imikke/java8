package ch9.ex10;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LabeledPointTest {

	@Test
	public void test() {
		LabeledPoint lp1 = new LabeledPoint("hoge", 1, 2);
		LabeledPoint lp2 = new LabeledPoint("foo", 1, 2);
		LabeledPoint lp3 = new LabeledPoint("hoge", 1, 2);
		LabeledPoint lp4 = new LabeledPoint("hoge", 2, 2);
		LabeledPoint lp5 = new LabeledPoint("hoge", 1, 1);

		assertEquals(1, lp1.compareTo(lp2));
		assertEquals(0, lp1.compareTo(lp1));
		assertEquals(0, lp1.compareTo(lp3));
		assertEquals(-1, lp1.compareTo(lp4));
		assertEquals(1, lp1.compareTo(lp5));
	}
}
