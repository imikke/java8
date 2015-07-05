package ch1.ex01;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class LambdaArraysTest {

	@Test
	public void test() {
		LambdaArrays arrays = new LambdaArrays();
		String[] expecteds = { "a", "had", "Mary", "lamb", "little" };
		String[] actuals = arrays.sortArrays("Mary had a little lamb"
				.split(" "));
		assertArrayEquals(expecteds, actuals);
	}

}
