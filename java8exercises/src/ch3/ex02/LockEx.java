package ch3.ex02;

import java.util.concurrent.locks.ReentrantLock;

public class LockEx {
	/**
	 * withLockメソッドは、lock付きでrunner.run()を実行する。
	 * 
	 * @param mylock
	 * @param runner
	 */
	public void withLock(ReentrantLock mylock, Runnable runner) {
		mylock.lock();
		try {
			runner.run();
		} finally {
			mylock.unlock();
		}
	}

}
