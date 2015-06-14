package ch1.ex1;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ch1.ex1.LambdaArrays;

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
