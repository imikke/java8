package ch3.ex16;

import org.junit.Test;

public class ExceptionExTest {

	@Test
	public void test() {
		String[] tests = { "a", "b" };
		ExceptionEx.doInOrder(() -> System.out.println(tests[2]),
				() -> System.out.println("Goodbye"),
				(e) -> System.out.println("Yikes: " + e));
		ExceptionEx.doInOrderAsync(() -> {
			return tests[3];
		}, (t) -> System.out.println(t),
				(e) -> System.out.println("Yikes: " + e));
		ExceptionEx.doInOrderAsync(() -> {
			return tests;
		}, (t, h) -> {
			if (h == null) {
				System.out.println(t[4]);
			} else {
				System.out.println("Yikes: " + h);
			}
		});
	}
}
