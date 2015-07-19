package ch3.ex02;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockExTest {

	@Test
	public void test() {
		ReentrantLock mylock = new ReentrantLock();
		LockEx le1 = new LockEx();
		LockEx le2 = new LockEx();
		for (int i = 0; i < 10; i++) {
			int j = i;
			le1.withLock(mylock, () -> {
				System.out.println(j + ": first lock");
				try {
					Thread.sleep(100);// 100 ms.
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			le2.withLock(mylock, () -> {
				System.out.println(j + ": second lock");
				try {
					Thread.sleep(100);// 100 ms.
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		/*
		 * 0: first lock
		 * 0: second lock
		 * 1: first lock
		 * 1: second lock
		 * 2: first lock
		 * 2: second lock
		 * 3: first lock
		 * 3: second lock
		 * 4: first lock
		 * 4: second lock
		 * 5: first lock
		 * 5: second lock
		 * 6: first lock
		 * 6: second lock
		 * 7: first lock
		 * 7: second lock
		 * 8: first lock
		 * 8: second lock
		 * 9: first lock
		 * 9: second lock
		 */
	}
}
