package ch1.ex07;

import org.junit.Test;

public class RunnableCombinationTest {

	@Test
	public void test() {
		RunnableCombination rc = new RunnableCombination();
		Runnable comb = rc.andThen(() -> System.out.println("first Runnable."),
				() -> System.out.println("second Runnable."));
		comb.run();
	}
}
