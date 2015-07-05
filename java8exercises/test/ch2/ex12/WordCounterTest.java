package ch2.ex12;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.Test;

public class WordCounterTest {

	@Test
	public void test() throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("test/words.txt")), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{Alnum}]+"));

		Stream<String> words = wordList.stream();
		AtomicInteger[] actual = WordCounter.countShortWords(words, 5);
		Integer[] expected = { 0, 1, 3, 4, 4 };
		// System.out.println(Arrays.toString(expected) + "," +
		// Arrays.toString(actual));
		assertEquals(Arrays.toString(expected), Arrays.toString(actual));
	}
}
