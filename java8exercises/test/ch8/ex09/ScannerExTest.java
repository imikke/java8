package ch8.ex09;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.Test;

public class ScannerExTest {

	@Test
	public void test() throws IOException {
		URL url = new URL("http://horstmann.com");
		String expected = "";
		String actual = "";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				url.openStream()))) {
			Stream<String> lines = reader.lines();
			Optional<String> imgEntry = lines.filter(s -> s.contains("<img "))
					.findFirst();
			expected = imgEntry.toString();
		}
		try (Scanner scanner = new Scanner(new InputStreamReader(
				url.openStream()))) {
			Stream<String> lines = ScannerEx.lines(scanner);
			Optional<String> imgEntry = lines.filter(s -> s.contains("<img "))
					.findFirst();
			actual = imgEntry.toString();
		}
		assertEquals(expected, actual);
		try (Scanner scanner = new Scanner(new File("test/words2.txt"))) {
			Stream<String> words = ScannerEx.words(scanner, "[\\P{Alnum}]+");
			long count = words.filter(s -> s.length() > 12).count();
			assertEquals(7L, count);
		}
		try (Scanner scanner = new Scanner(new File("test/numbers.txt"))) {
			Stream<Integer> nums = ScannerEx.integers(scanner, ",");
			Optional<Integer> sum = nums.reduce(Integer::sum);
			assertEquals(5, sum.get().intValue());
		}
		try (Scanner scanner = new Scanner(new File("test/numbers2.txt"))) {
			Stream<Double> nums = ScannerEx.doubles(scanner, ",");
			Optional<Double> sum = nums.reduce(Double::sum);
			assertEquals(5.0, sum.get().doubleValue(), 1.0);
		}
	}
}
