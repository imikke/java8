package ch8.ex06;

import static org.junit.Assert.assertEquals;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import org.junit.Test;

public class Comparing2DTest {

	@Test
	public void test() {
		// Point2D
		Point2D p1 = new Point2D(1, 1);
		Point2D p2 = new Point2D(1, 1);
		int actual = Comparing2D.comparePoint2D(p1, p2);
		assertEquals(0, actual);
		p1 = new Point2D(0, 1);
		actual = Comparing2D.comparePoint2D(p1, p2);
		assertEquals(-1, actual);
		p1 = new Point2D(1, 0);
		actual = Comparing2D.comparePoint2D(p1, p2);
		assertEquals(-1, actual);
		p1 = new Point2D(2, 1);
		actual = Comparing2D.comparePoint2D(p1, p2);
		assertEquals(1, actual);
		p1 = new Point2D(1, 2);
		actual = Comparing2D.comparePoint2D(p1, p2);
		assertEquals(1, actual);

		// Rectangle2D
		Rectangle2D r1 = new Rectangle2D(0, 0, 1, 1);
		Rectangle2D r2 = new Rectangle2D(0, 0, 1, 1);
		actual = Comparing2D.compareRectangle2D(r1, r2);
		assertEquals(0, actual);
		r1 = new Rectangle2D(0, 0, 0, 1);
		actual = Comparing2D.compareRectangle2D(r1, r2);
		assertEquals(-1, actual);
		r1 = new Rectangle2D(0, 0, 1, 0);
		actual = Comparing2D.compareRectangle2D(r1, r2);
		assertEquals(-1, actual);
		r1 = new Rectangle2D(0, 0, 2, 1);
		actual = Comparing2D.compareRectangle2D(r1, r2);
		assertEquals(1, actual);
		r1 = new Rectangle2D(0, 0, 1, 2);
		actual = Comparing2D.compareRectangle2D(r1, r2);
		assertEquals(1, actual);
	}

}
