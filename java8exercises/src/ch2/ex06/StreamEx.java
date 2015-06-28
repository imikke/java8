package ch2.ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx {
	public static <T> void show(String title, Stream<T> stream) {
		final int SIZE = 10;
		List<T> firstElements = stream.limit(SIZE + 1).collect(
				Collectors.toList());
		System.out.print(title + ": ");
		if (firstElements.size() <= SIZE)
			System.out.println(firstElements);
		else {
			firstElements.remove(SIZE);
			String out = firstElements.toString();
			System.out.println(out.substring(0, out.length() - 1) + ", ...]");
		}
	}

	public static Stream<Character> characterStream(String s) {
		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray())
			result.add(c);
		return result.stream();
	}

	/**
	 * characterStreamExメソッドは、文字列sをCharacterのストリームに変換する。
	 * 
	 * @param s
	 * @return
	 */
	public static Stream<Character> characterStreamEx(String s) {
		return IntStream.range(0, s.length()).map(s::charAt)
				.mapToObj(w -> (char) w);

	}
}
