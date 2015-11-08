package ch9.ex09;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LabeledPointTest {

	@Test
	public void test() {
		LabeledPoint lp1 = new LabeledPoint("hoge", 1, 2);
		LabeledPoint lp2 = new LabeledPoint("foo", 1, 2);
		LabeledPoint lp3 = new LabeledPoint("hoge", 1, 2);

		assertEquals(false, lp1.equals(lp2));
		assertEquals(true, lp1.equals(lp1));
		assertEquals(true, lp1.equals(lp3));
	}

}
