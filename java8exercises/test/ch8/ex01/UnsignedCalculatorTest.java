package ch8.ex01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnsignedCalculatorTest {

	@Test
	public void test() {
		int x1 = Integer.MAX_VALUE;
		int x2 = Integer.MAX_VALUE * 2; // 1と同じ
		long plus = UnsignedCalculator.unsignedPlus(x1, x2);
		assertEquals(Integer.MAX_VALUE * 3L, plus);

		long minus = UnsignedCalculator.unsignedMinus(x2, x1);
		assertEquals(Integer.MAX_VALUE, minus);

		int x3 = Integer.MAX_VALUE * 2;
		// Integer.MAX_VALUEを超える値の除算を正しく行うためにdevideUnsigned、remainderUnsignedが必要
		int devide = UnsignedCalculator.unsignedDevide(x3, 2);
		assertEquals(Integer.MAX_VALUE, devide); // 結果：Integer.MAX_VALUE
		// System.out.println(x3 / 2); // 結果：-2 / 2 = -1
		int remainder = UnsignedCalculator.unsignedRemainder(2, x3);
		assertEquals(2, remainder); // 結果：2
		// System.out.println(2 % x3); // 結果：2 % -2 = 0

		int compare = UnsignedCalculator.unsignedCompare(x1, x2);
		assertEquals(-1, compare);
	}
}
