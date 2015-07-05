package ch2.ex09;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamEx {

	/**
	 * collectStreamメソッドは、ArrayListのストリームを一つのArrayListに集約する。
	 * 
	 * @param stream
	 * @return
	 */
	public static <T> ArrayList<T> collectStream(Stream<ArrayList<T>> stream) {
		// xとyの2つのストリームを結合して最後に（3つ目の引数によって）集約する
		return stream.reduce(new ArrayList<T>(), (x, y) -> {
			x.addAll(y);
			return x;
		}, (total1, total2) -> {
			total1.addAll(total2);
			return total1;
		});
	}
}
