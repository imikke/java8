package ch1.ex06;

import org.junit.Test;

public class UncheckingTest {

	@Test
	public void test() {
		new Thread(Unckecking.uncheck(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
		})).start();
	}
}
