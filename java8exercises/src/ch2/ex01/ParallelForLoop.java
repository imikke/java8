package ch2.ex01;

import java.util.List;

/**
 * ParallelForLoopクラスは、for文のループ処理を並行で行うクラスである。
 */
public class ParallelForLoop {

	static Counter counter = new Counter();

	public int countWords(List<String> words, int length) {
		Thread thread[] = new Thread[words.size()];
		for (int i = 0; i < words.size(); i++) {
			String w = words.get(i);
			Runnable runner = () -> {
				if (w.length() > length)
					counter.count();
			};
			thread[i] = new Thread(runner);
			thread[i].start();
		}
		// wait until all thread end.
		for (Thread th : thread) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return counter.getCount();
	}
}

class Counter {
	private int count;

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