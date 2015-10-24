package ch8.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FilterTest {

	public static AtomicInteger atomicCount = new AtomicInteger(0);

	public static void main(String[] args) throws IOException {
		final int LOOP_NUM = 10;
		String contents = new String(Files.readAllBytes(Paths
				.get("test/alice.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		Instant start = Instant.now();
		for (int i = 0; i < LOOP_NUM; i++) {
			words.forEach(w -> {
				if (w.length() > 12)
					atomicCount.addAndGet(1);
			});
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("List: " + timeElapsed.toMillis() / LOOP_NUM + " ms"
				+ ", Total count: " + atomicCount.get());

		long count = 0;
		start = Instant.now();
		for (int i = 0; i < LOOP_NUM; i++) {
			count += words.stream().filter(w -> w.length() > 12).count();
		}
		end = Instant.now();
		timeElapsed = Duration.between(start, end);
		System.out.println("Stream: " + timeElapsed.toMillis() / LOOP_NUM
				+ " ms" + ", Total count: " + count);

		/*
		 * Result:
		 * List: 30 ms, Total count: 330
		 * Stream: 3 ms, Total count: 330
		 */

	}
}
