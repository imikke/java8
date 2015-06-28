package ch2.ex08;

import java.util.stream.Stream;

public class StreamUtil {
	public static <T> boolean isFinite(Stream<T> stream) {
		final long SIZE = 10000000L;
		if (stream.limit(SIZE).count() < SIZE) {
			return true;
		}
		return false;
	}
}
