package ch2.ex03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StopWatchTest {

	@Test
	public void test() {
		StopWatch sw = new StopWatch();
		String contents = null;
		// alice.txtは、本のサンプルプログラムを参照
		try {
			contents = new String(Files.readAllBytes(Paths
					.get("test/alice.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		long sum = 0;
		int t = 10000;
		for (int i = 0; i < t; i++) {
			sum += sw.countWordsOfStream(words, w -> (w.length() > 12));
		}
		long time = sum / t;
		sum = 0;
		System.out.println("stream " + time + " ns");

		for (int i = 0; i < t; i++) {
			sum += sw.countWordsOfParallelStream(words, w -> (w.length() > 12));
		}
		time = sum / t;
		System.out.println("parallelStream " + time + " ns");

	}

}
