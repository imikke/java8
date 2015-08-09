package ch3.ex21;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class FutureExTest {

	@Test
	public void test() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future1 = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(1000);
				return 12345;
			}
		});
		Future<String> future2 = FutureEx.map(future1, (i) -> {
			return i.toString();
		});
		int count = 0;
		while (!future2.isDone()) {
			System.out.println("not done");
			// count > 5にするとキャンセルされる
			if (count > 10) {
				future2.cancel(true);
				System.out.println("cancele");
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
		if (future2.isCancelled()) {
			System.out.println("canceled");
			return;
		}
		try {
			assertEquals("12345", future2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
