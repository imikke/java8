package ch6.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicUpdates {
	public static int ntasks = 10000;

	public static AtomicReferenceArray<String> stringArray;
	public static AtomicReference<String> largest = new AtomicReference<>("");

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		String contents = null;
		try {
			contents = new String(Files.readAllBytes(Paths
					.get("test/words2.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		stringArray = new AtomicReferenceArray<String>(
				contents.split("[\\P{Alnum}]+"));

		for (int t = 0; t < ntasks; t++) {
			pool.submit(() -> {
				for (int i = 0; i < stringArray.length(); i++) {
					String observed = stringArray.get(i);
					if (observed == null)
						observed = "";
					String oldValue = "", newValue = "";
					int count = 0;
					do {
						if (count > 0)
							System.out.println("conflict :" + oldValue);
						oldValue = largest.get();
						// 同じ長さの文字列の場合、後にあるものを優先
						if (oldValue.length() <= observed.length()) {
							newValue = observed;
						} else {
							newValue = oldValue;
						}
						count++;
					} while (!largest.compareAndSet(oldValue, newValue));
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
		// 最終的にはacknowledgmentになれば正解
		System.out.println("---");
		System.out.println("largest : " + largest);
	}
}
