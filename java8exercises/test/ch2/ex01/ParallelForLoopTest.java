package ch2.ex01;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ParallelForLoopTest {

	@Test
	public void test() {
		ParallelForLoop pfl = new ParallelForLoop();
		String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths
					.get("test/words.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		int count = 0;
		for (String w : words) {
			if (w.length() > 5)
				count++;
		}
		System.out.println(count);

		int actual = pfl.countWords(words, 5);
		assertEquals(count, actual);
	}
}
