package ch8.ex01;

/**
 * UnsignedCalculatorクラスは、int値を符号なしint値として計算する。
 */
public class UnsignedCalculator {

	static long unsignedPlus(int x1, int x2) {
		return Integer.toUnsignedLong(x1) + Integer.toUnsignedLong(x2);
	}

	static long unsignedMinus(int x1, int x2) {
		return Integer.toUnsignedLong(x1) - Integer.toUnsignedLong(x2);
	}

	static int unsignedDevide(int x1, int x2) {
		return Integer.divideUnsigned(x1, x2);
	}

	static int unsignedRemainder(int x1, int x2) {
		return Integer.remainderUnsigned(x1, x2);
	}

	static int unsignedCompare(int x1, int x2) {
		return Integer.compareUnsigned(x1, x2);
	}
}
