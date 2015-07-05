package ch2.ex10;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamCalculatorTest {

	@Test
	public void test() {
		Stream<Double> stream = Stream.of(1.0, 2.0, 3.0, 4.0, 5.0);
		Double ave = StreamCalculator.average(stream);
		// System.out.println(ave);
		assertTrue(Double.compare(3.0, ave.doubleValue()) == 0);
	}
}
