package ch3.ex23;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PairTest {

	@Test
	public void test() {
		Pair<Integer> pair1 = new Pair<>(1, 2);
		Pair<String> pair2 = Pair.map(pair1, (i) -> {
			return i.toString();
		});
		assertEquals("1", pair2.first());
		assertEquals("2", pair2.second());
	}
}
