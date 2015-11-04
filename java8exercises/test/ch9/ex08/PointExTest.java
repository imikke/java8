package ch9.ex08;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointExTest {

	@Test
	public void test() {
		PointEx p1 = new PointEx(1, 1);
		PointEx p2 = new PointEx(1, 1);
		assertEquals(0, p1.compareTo(p2));
		p2 = new PointEx(2, 1);
		assertEquals(-1, p1.compareTo(p2));
		p2 = new PointEx(-2, 1);
		assertEquals(1, p1.compareTo(p2));
		p1 = new PointEx(Integer.MAX_VALUE, 1);
		p2 = new PointEx(-1, 1);
		assertEquals(1, p1.compareTo(p2));
	}

}
