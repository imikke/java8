package ch2.ex13;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

public class WordCounterTest {

	@Test
	public void test() throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("test/words.txt")), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{Alnum}]+"));

		Stream<String> words = wordList.stream();
		Map<Integer, Long> actual = WordCounter.countShortWords(words, 5);
		Long[] expected = { 0L, 1L, 3L, 4L, 4L };
		assertEquals(expected.length - 1, actual.size());
		for (int key : actual.keySet()) {
			assertEquals(expected[key], actual.get(key));
		}
	}
}
