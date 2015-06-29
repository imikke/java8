package ch2.ex07;

import java.util.stream.Stream;

public class StreamEx {
	public static <T> boolean isFinite(Stream<T> stream) {
		final long SIZE = 10000000L;
		if (stream.limit(SIZE).count() < SIZE) {
			return true;
		}
		return false;
	}
}
