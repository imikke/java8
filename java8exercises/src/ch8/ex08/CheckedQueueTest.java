package ch8.ex08;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class CheckedQueueTest {
	public static Queue getMoreWork(Queue q) {
		q.add(new String("test/words2.txt"));
		return q;
	}

	public static void main(String[] args) {
		/*
		 * 実行時にStringからPathへキャストしようとして、java.lang.ClassCastExceptionが発生する
		 * Exception in thread "main" java.lang.ClassCastException:
		 * java.lang.String cannot be cast to java.nio.file.Path at
		 * ch8.ex08.CheckedQueueTest.main(CheckedQueueTest.java:21)
		 */
		Queue<Path> q1 = new LinkedList<Path>();
		q1.add(Paths.get("test/words.txt"));
		Queue<Path> error_q = CheckedQueueTest.getMoreWork(q1);
		Path p1 = error_q.poll();
		// p1 = error_q.poll(); //StringからPathへのキャスト

		/*
		 * オブジェクトの生成にcheckedQueueを使うことで、コンパイルエラーとして検知できる
		 * Exception in thread "main" java.lang.ClassCastException:
		 * Attempt to insert class java.lang.String element into collection with
		 * element type interface java.nio.file.Path at
		 * java.util.Collections$CheckedCollection.typeCheck(Collections.java:
		 * 3037)
		 * at java.util.Collections$CheckedCollection.add(Collections.java:3080)
		 * at ch8.ex08.CheckedQueueTest.getMoreWork(CheckedQueueTest.java:11)
		 * at ch8.ex08.CheckedQueueTest.main(CheckedQueueTest.java:29)
		 */
		Queue<Path> q2 = Collections.checkedQueue(new LinkedList<Path>(),
				Path.class);
		q2.add(Paths.get("test/words.txt"));
		Queue<Path> error_q2 = CheckedQueueTest.getMoreWork(q2);
		Path p2 = error_q2.poll();
		// p2 = error_q.poll();
	}
}
