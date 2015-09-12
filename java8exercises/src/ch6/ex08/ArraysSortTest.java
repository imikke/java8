package ch6.ex08;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class ArraysSortTest {

	public static String[] createArray(int length) {
		String array[] = new String[length];
		int i = length;
		while (i > 0) {
			array[i - 1] = Long.toString(i);
			i--;
		}
		return array;
	}

	public static void main(String[] args) throws IOException {
		int wins = 0;
		for (int i = 10000; i < 100000; i += 1000) {

			long sum = 0;
			int count = 0;
			final int MAX_VALUE = 100;
			String[] array = null;
			Duration timeElapsed = null;
			while (count < MAX_VALUE) {
				array = createArray(i);
				Instant start = Instant.now();
				Arrays.parallelSort(array);
				Instant end = Instant.now();
				timeElapsed = Duration.between(start, end);
				// System.out.println(count + ":" + timeElapsed.toMillis() +
				// "ms");
				sum += timeElapsed.toNanos();
				count++;
			}
			// System.out.println("parallelSort " + i + " : " + sum / MAX_VALUE
			// + "ns");
			long parallel = sum / MAX_VALUE;
			sum = 0;
			count = 0;
			timeElapsed = null;
			while (count < MAX_VALUE) {
				array = createArray(i);
				Instant start = Instant.now();
				Arrays.sort(array);
				Instant end = Instant.now();
				timeElapsed = Duration.between(start, end);
				// System.out.println(count + ":" + timeElapsed.toMillis() +
				// "ms");
				sum += timeElapsed.toNanos();
				count++;
			}
			long single = sum / MAX_VALUE;
			// System.out.println("sort " + i + ":" + sum / MAX_VALUE + "ns");

			// 5回連続で並列処理が勝つまで
			if (parallel < single) {
				wins++;
				// System.out.println(wins);
			} else {
				wins = 1;
			}
			if (wins >= 5) {
				System.out.println(i + " words :  parallel " + parallel
						+ "ns, single: " + single + "ns");
				break;
			}
		}
		/*
		 * Result:
		 * 22000 words : parallel 1220000ns, single: 1300000ns
		 * 24000 words : parallel 1690000ns, single: 1800000ns
		 * 24000 words : parallel 1380000ns, single: 1410000ns
		 */
	}

}
