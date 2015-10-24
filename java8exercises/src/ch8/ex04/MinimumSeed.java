package ch8.ex04;

import java.util.Random;
import java.util.stream.Stream;

public class MinimumSeed {
	public static long prev(long seed) {
		long a = 11;
		long v = 246154705703781L;
		long N = (long) Math.pow(2, 48);
		return (seed - a) * v % N;
	}

	public static void main(String[] args) {
		Stream<Long> seeds = Stream.iterate(0L, sd -> prev(sd));
		seeds.limit(1000000).map(sd -> sd ^ 25214903917L).sorted((s1, s2) -> {
			if (Math.abs(s1) > Math.abs(s2)) {
				return 1;
			} else if (s1 == s2) {
				return 0;
			} else {
				return -1;
			}
		}).limit(10).forEach(sd -> System.out.println(sd));
		/*
		 * Resutl:
		 * 881498
		 * -250544011
		 * 1593254785
		 * -1748124353
		 * 1783840086
		 * -2244715853
		 * 2407377035
		 * -2678684470
		 * 2721743442
		 * -3752455426
		 */
		// long s = prev(prev(prev(0))) ^ 25214903917L;
		// Random generator = new Random(s);
		// 881498をseedにしてnextDoubleを繰り返し呼ぶ376050回で0となる
		Random generator = new Random(881498);
		for (int tries = 1; tries < 1000000000; tries++) {
			double r = 1.0 - generator.nextDouble();
			if (r == 1.0) {
				System.out.println("It happened at try " + tries);
				break;
			}
		}
	}
}
