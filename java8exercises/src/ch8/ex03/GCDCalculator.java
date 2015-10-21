package ch8.ex03;

public class GCDCalculator {

	/**
	 * gcdメソッドは、ユークリッドのアルゴリズムを%で余りで実装した結果を返す。
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static public int gcd(int a, int b) {
		a = Math.abs(a);
		b = Math.abs(b);
		if (a < b) {
			throw new IllegalArgumentException("abs(a) is bigger than abs(b)");
		}
		if (b == 0) {
			return a;
		}
		return GCDCalculator.gcd(b, a % b);
	}

	/**
	 * gcdFloorModメソッドは、ユークリッドのアルゴリズムをfloorModで余りで実装した結果を返す。
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static public int gcdFloorMod(int a, int b) {
		if (Math.abs(a) < Math.abs(b)) {
			throw new IllegalArgumentException("abs(a) is bigger than abs(b)");
		}
		if (b == 0) {
			return a;
		}
		return GCDCalculator.gcd(b, Math.floorMod(a, b));
	}

	/**
	 * gcdRemメソッドは、ユークリッドのアルゴリズムを数学的な（負ではない）余りで実装した結果を返す。
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static public int gcdRem(int a, int b) {
		a = Math.abs(a);
		b = Math.abs(b);
		if (a < b) {
			throw new IllegalArgumentException("abs(a) is bigger than abs(b)");
		}
		if (b == 0) {
			return a;
		}
		return GCDCalculator.gcd(b, GCDCalculator.rem(a, b));
	}

	private static int rem(int a, int b) {
		return Integer.remainderUnsigned(a, b);
	}
}
