package ch6.ex06;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ConcurrentFileReader {
	public static int ntasks = 10;
	public static ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

	public static void main(String[] args) throws IOException,
			InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();

		File files[] = new File[2];
		files[0] = new File("test/words.txt");
		files[1] = new File("test/words2.txt");
		long threshold = 1;

		for (int t = 0; t < ntasks; t++) {
			pool.submit(() -> {
				for (int i = 0; i < files.length; i++) {
					String contents;
					try {
						contents = new String(Files.readAllBytes(Paths
								.get(files[i].getPath())),
								StandardCharsets.UTF_8);
						Stream<String> words = Stream.of(contents
								.split("[\\P{Alnum}]+"));
						int j = i;
						words.parallel().forEach(w -> {
							map.computeIfAbsent(w, (f1) -> {
								Set<File> set = new HashSet<File>();
								set.add(files[j]);
								return set;
							}).add(files[j]);
						});
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
		map.forEach(threshold,
				(k, v) -> System.out.println(k + " -> " + v + ", "));
	}
}
