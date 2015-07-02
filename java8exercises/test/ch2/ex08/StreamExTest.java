package ch2.ex08;

import static org.junit.Assert.assertArrayEquals;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamExTest {

	@Test
	public void test() {
		Stream<Integer> stream1 = IntStream.range(0, 10).boxed();
		Stream<Integer> stream2 = IntStream.range(0, 5).boxed();
		// DoubleStream stream3 = DoubleStream.of(1.0, 2.0, 3.0);
		// Stream<Double> randoms = Stream.generate(Math::random);
		Stream<Integer> actual = StreamEx.zip(stream1, stream2);
		Object[] results = actual.toArray();
		Object[] expected = { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4 };
		assertArrayEquals(expected, results);
		// 無限ストリームの場合
		// Stream<Double> actual = StreamEx.zip(stream3.boxed(), randoms);
		// actual.forEach(System.out::println);

	}
}
