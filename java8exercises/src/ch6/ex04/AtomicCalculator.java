package ch6.ex04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;

public class AtomicCalculator {

	public static int ntasks = 1000;
	public static int iterations = 1000;

	public static AtomicLong nextNumber = new AtomicLong(ntasks * iterations
			/ 2);

	public static LongAccumulator maxValue = new LongAccumulator(Math::max, 0);
	public static LongAccumulator minValue = new LongAccumulator(Math::min, 0);

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int t = 0; t < ntasks; t++)
			pool.submit(() -> {
				long start = nextNumber.incrementAndGet();
				for (int i = 0; i < iterations; i++) {
					long observed = (start + ntasks * i)
							% (ntasks * iterations);
					maxValue.accumulate(observed);
					minValue.accumulate(observed);
				}
			});
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("MAX: " + maxValue);
		System.out.println("MIN: " + minValue);
	}

}
