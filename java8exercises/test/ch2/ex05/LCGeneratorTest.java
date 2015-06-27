package ch2.ex05;

import java.util.stream.Stream;

import org.junit.Test;

public class LCGeneratorTest {

	@Test
	public void test() {
		LCGenerator lcg = new LCGenerator();

		long a = 25214903917L;
		long c = 11;
		long m = Double.valueOf(Math.pow(2, 48)).longValue();
		Stream<Long> randams = lcg.generateRandoms(123456789, a, c, m);
		randams.limit(10).forEach(System.out::println);
	}

}
