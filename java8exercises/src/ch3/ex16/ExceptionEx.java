package ch3.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionEx {
	public static <T> void doInOrderAsync(Supplier<T> first,
			Consumer<T> second, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					T result = first.get();
					second.accept(result);
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t.start();
	}

	/**
	 * doInOrderAsyncメソッドは、Supplierで得た値をBiConsumerで消費するメソッドである。
	 * 
	 * @param first
	 * @param second
	 *            例外処理がない場合はacceptの第2引数はnull
	 */
	public static <T> void doInOrderAsync(Supplier<T> first,
			BiConsumer<T, Throwable> second) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					T result = first.get();
					second.accept(result, null);
				} catch (Throwable t) {
					second.accept(null, t);
				}
			}
		};
		t.start();
	}

	public static void doInOrder(Runnable first, Runnable second,
			Consumer<Throwable> handler) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					first.run();
					second.run();
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t.start();
	}

}
