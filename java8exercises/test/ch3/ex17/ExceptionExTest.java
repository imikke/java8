package ch3.ex17;

import org.junit.Test;

public class ExceptionExTest {

	@Test
	public void test() {
		String[] tests = { "a", "b" };
		ExceptionEx.doInOrder(() -> System.out.println(tests[2]),
				() -> System.out.println("Goodbye"),
				(e) -> System.out.println("Yikes: " + e));

		ExceptionEx.doInParallelAsync(() -> {
			System.out.println(tests[3]);
		}, () -> System.out.println(tests[4]),
				(e) -> System.out.println("Yikes: " + e));
	}
}
