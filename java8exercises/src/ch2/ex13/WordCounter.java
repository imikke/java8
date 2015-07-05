package ch2.ex13;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Map;
import java.util.stream.Stream;

public class WordCounter {

	/**
	 * countShortWordsメソッドは、文字列のストリームに対して、lenより短い長さの文字列をカウントして
	 * Mapとして返す。keyが文字列の長さに対応する。
	 * 
	 * @param words
	 * @param len
	 * @return
	 * @return
	 */
	public static Map<Integer, Long> countShortWords(Stream<String> words,
			int len) {
		return words.parallel().filter(s -> s.length() < len)
				.collect(groupingBy(String::length, counting()));
	}
}
