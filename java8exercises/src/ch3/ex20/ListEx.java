package ch3.ex20;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface ListEx<T> {
	/**
	 * mapメソッドはList<T>の各要素にFunction<T,U> fを適用して、List<U>を作成する。
	 * 
	 * @param list
	 * @param f
	 * @return
	 */
	public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
		return list.stream().map((t) -> f.apply(t))
				.collect(Collectors.toList());
	}
}
