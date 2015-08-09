package ch3.ex21;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class FutureEx<T> {
	/**
	 * mapメソッドはFuture<T>にFunction<T, U>を適用して、Future<U>を作成する。
	 * 
	 * @param future
	 * @param f
	 * @return
	 */
	public static <T, U> Future<U> map(Future<T> future, Function<T, U> f) {
		return new Future<U>() {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return f.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException,
					TimeoutException {
				return f.apply(future.get(timeout, unit));
			}

		};
	}
}
