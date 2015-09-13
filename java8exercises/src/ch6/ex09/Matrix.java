package ch6.ex09;

import java.util.Arrays;

public class Matrix {
	/**
	 * fibonacciメソッドは、n番目のフィボナッチ数列の値を返す。
	 * 
	 * @param n
	 * @return
	 * @exception IllegalArgumentException
	 *                引数nが負の場合
	 */
	public static long fibonacci(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("`n` argument is bigger than 0.");
		}
		// parallelSetAll
		long arrays[][][] = new long[n][2][2];
		Arrays.parallelSetAll(arrays, i -> {
			long array[][] = new long[2][2];
			array[0][0] = 1;
			array[0][1] = 1;
			array[1][0] = 1;
			array[1][1] = 0;
			return array;
		});
		/*
		 * for (long e[][] : arrays) {
		 * System.out.println(e[0][0] + "," + e[1][0] + "," + e[1][0] + ","
		 * + e[1][1]);
		 * }
		 */

		Arrays.parallelPrefix(arrays,
				(matrix1, matrix2) -> Matrix.multiFibonacci(matrix1, matrix2));
		return arrays[n - 1][0][0];
	}

	private static long[][] multiFibonacci(long[][] matrix1, long[][] matrix2) {
		if (matrix1 == null || matrix2 == null) {
			throw new NullPointerException(
					"`matrix1` and `matrix2` arguments must be non-null.");
		}
		long[][] result = new long[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					result[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		return result;
	}
}
