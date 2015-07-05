package ch2.ex10;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamCalculator {
	static Counter counter = new Counter();

	public static <T> Double average(Stream<Double> stream) {
		// long count = stream.count();
		Optional<Double> sum = stream.reduce((x, y) -> {
			counter.count();
			return x + y;
		});
		// return sum.get() / count;
		// 要素数はcount+1
		return sum.get() / (counter.getCount() + 1);
	}
}

class Counter {
	private int count = 0;

	// countメソッドは、複数のスレッドから呼ばれるので同期を取る
	synchronized void count() {
		int n = count;
		count = n + 1;
		// System.out.println(count);
	}

	public int getCount() {
		return count;
	}
}