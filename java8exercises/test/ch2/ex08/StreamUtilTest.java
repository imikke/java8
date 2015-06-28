package ch2.ex08;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamUtilTest {

	@Test
	public void test() {
		String[] strings = { "row", "row", "row", "your", "boat", "gently",
				"down", "the", "stream" };
		// 有限のストリーム
		Stream<String> song = Stream.of(strings);
		assertTrue(StreamUtil.isFinite(song));
		// 無限のストリーム
		Stream<Double> randoms = Stream.generate(Math::random);
		assertFalse(StreamUtil.isFinite(randoms));
	}

}
