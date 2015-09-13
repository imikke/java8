package ch6.ex09;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void test() {
		long fibonacci = Matrix.fibonacci(10);
		assertEquals(89, fibonacci);

		fibonacci = Matrix.fibonacci(15);
		assertEquals(987, fibonacci);
	}
}
