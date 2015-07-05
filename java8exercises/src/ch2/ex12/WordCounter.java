package ch2.ex12;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class WordCounter {

	/**
	 * countShortWordsメソッドは、文字列のストリームに対して、lenより短い長さの文字列をカウントして
	 * AtomicIntegerの配列として返す。 配列のindexが文字列の長さに対応する。
	 * 
	 * @param words
	 * @param len
	 * @return
	 */
	public static AtomicInteger[] countShortWords(Stream<String> words, int len) {
		AtomicInteger[] shortWordCounters = new AtomicInteger[len];
		for (int i = 0; i < shortWordCounters.length; i++) {
			shortWordCounters[i] = new AtomicInteger();
		}
		words.parallel().forEach(s -> {
			if (s.length() < len) {
				// System.out.println(s.length() + ":" + s);
				shortWordCounters[s.length()].getAndIncrement();
			}
		});
		return shortWordCounters;
	}
}
