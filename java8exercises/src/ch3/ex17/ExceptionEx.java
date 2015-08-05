package ch3.ex17;

import java.util.function.Consumer;

public class ExceptionEx {
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

	/**
	 * doInParallelAsyncメソッドは、firstとsecondを並列に実行する。
	 * 例外が発生したらhandlerを呼び出す。
	 * 
	 * @param first
	 * @param second
	 * @param handler
	 */
	public static void doInParallelAsync(Runnable first, Runnable second,
			Consumer<Throwable> handler) {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					first.run();
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					second.run();
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t1.start();
		t2.start();
	}
}
