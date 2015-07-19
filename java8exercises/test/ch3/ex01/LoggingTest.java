package ch3.ex01;

import java.util.logging.Level;

import org.junit.Test;

public class LoggingTest {

	@Test
	public void test() {
		int[] a = { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
		for (int j = 0; j < a.length; j++) {
			int i = j;
			// デフォルトでは、FINESTはロギングされない
			Logging.logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]);
			// INFOはロギングされる
			Logging.logIf(Level.INFO, () -> i == 10, () -> "a[10] = " + a[10]);

			/*
			 * INFO is loggable.
			 * 7 19, 2015 11:33:56 午前 ch3.ex01.Logging logIf
			 * 情報: a[10] = 100
			 */
		}
	}
}
