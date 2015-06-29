package ch2.ex08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx {

	/**
	 * zipメソッドは、firstとsecondの2つのストリームの要素を交互に取得して、別のストリームにして返す。
	 * どちらかのストリームが終了するか、要素数が最大値を（SIZE）超えた場合に処理を終了して、ストリームを返す。
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		final long SIZE = 1000000L;
		List<T> firstList = first.limit(SIZE).collect(Collectors.toList());
		List<T> secondList = second.limit(SIZE).collect(Collectors.toList());
		int arrayLen = 0;
		if (firstList.size() < secondList.size()) {
			arrayLen = firstList.size();
		} else {
			arrayLen = secondList.size();
		}
		List<T> results = new ArrayList<T>();
		for (int i = 0; i < arrayLen; i++) {
			results.add(firstList.get(i));
			results.add(secondList.get(i));
		}

		return results.stream();
	}
}
