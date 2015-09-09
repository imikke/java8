package ch6.ex07;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class MaximumEntry {

	public static void main(String[] args) throws IOException {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		// alice.txtは、本のサンプルプログラムを参照
		Path path = Paths.get("test/alice.txt");
		String contents = new String(Files.readAllBytes(path),
				StandardCharsets.UTF_8);
		Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));
		words.parallel().forEach(w -> map.merge(w, 1L, Long::sum));

		long threshold = 1;

		Map.Entry<String, Long> maxValue = map.reduceEntries(threshold,
				(m1, m2) -> {
					if (m1.getValue() > m2.getValue()) {
						return m1;
					}
					return m2;
				});
		System.out.println("Max key, value: " + maxValue.getKey() + ", "
				+ maxValue.getValue());
	}
}
