package ch2.ex03;

import java.util.List;
import java.util.function.Predicate;

public class StopWatch {

	public long countWordsOfStream(List<String> words,
			Predicate<String> predicate) {
		long start = System.nanoTime();
		words.stream().filter(predicate).count();
		long end = System.nanoTime();
		return end - start;
	}

	public long countWordsOfParallelStream(List<String> words,
			Predicate<String> predicate) {
		long start = System.nanoTime();
		words.parallelStream().filter(predicate).count();
		long end = System.nanoTime();
		return end - start;
	}
}
