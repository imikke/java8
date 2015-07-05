package ch2.ex11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayListTest {
	public static void main(String[] strArray) {
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		// toListは、複数のArraysListを作ってマージしている
		List<Integer> list = stream.collect(Collectors.toList());
		for (Integer e : list) {
			System.out.println(e);
		}

		// setを使って集約
		Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		int SIZE = 5;
		for (int i = 0; i < SIZE; i++) {
			list2.add(0);
		}
		// 要素の位置が不明
		stream2.forEach(x -> list2.set(x - 1, x));
		for (Integer e : list2) {
			System.out.println(e);
		}

	}
}
