package ch2.ex09;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamExTest {

	@Test
	public void test() {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		int size = 100;
		for (int i = 0; i < size; i++) {
			list1.add(Integer.valueOf(i));
			list2.add(Integer.valueOf(i + size));
			list3.add(Integer.valueOf(i + size * 2));
		}
		Stream<ArrayList<Integer>> stream = Stream.of(list1, list2, list3);
		ArrayList<Integer> actual = StreamEx.collectStream(stream);
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (int i = 0; i < size * 3; i++) {
			expected.add(i);
		}
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
}
