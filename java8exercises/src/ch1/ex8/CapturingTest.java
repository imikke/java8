package ch1.ex8;

import java.util.ArrayList;
import java.util.List;

/**
 * CapturingTestクラスは、ラムダ式のキャプチャのテストを実施する。
 */
public class CapturingTest {
	public static void main(String[] strArray) {
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
		for (String name : names)
			runners.add(() -> System.out.println(name));

		// 以下のfor文の場合、キャプチャされた変数iが変更してしまうのでコンパイルエラーとなる。
		// for (int i = 0; i < names.length; i++) {
		// runners.add(() -> System.out.println(names[i]));
		// }

		for (Runnable runner : runners) {
			runner.run();
		}

	}
}
