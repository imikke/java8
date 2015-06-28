package ch2.ex06;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamExTest {

	@Test
	public void test() {

		String[] strings = { "row", "row", "row", "your", "boat", "gently",
				"down", "the", "stream" };
		Stream<String> song = Stream.of(strings);
		Stream<Character> letters = song.flatMap(w -> StreamEx
				.characterStream(w));
		// StreamEx.show("letters", letters);
		Stream<String> song2 = Stream.of(strings);
		Stream<Character> letters2 = song2.flatMap(w -> StreamEx
				.characterStreamEx(w));
		// StreamEx.show("letters", letters2);
		List<Character> list1 = letters.collect(Collectors.toList());
		List<Character> list2 = letters2.collect(Collectors.toList());
		assert (list1.equals(list2));
	}
}
