package ch3.ex20;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StreamExTest {

	@Test
	public void test() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		List<String> list2 = StreamEx.map(list1, (i) -> {
			return i.toString();
		});
		List<String> expected = new ArrayList<>();
		expected.add("1");
		expected.add("2");
		assertArrayEquals(expected.toArray(), list2.toArray());
	}

}
