package ch3.ex23;

import java.util.function.Function;

public class Pair<T> {

	private T first;
	private T second;

	Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * mapメソッドはPair<T>にFunction<T, U>を適用して、Pair<U>を作成する。
	 * 
	 * @param pair
	 * @param f
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T, U> Pair<U> map(Pair<T> pair, Function<T, U> f) {
		return new Pair(f.apply(pair.first), f.apply(pair.second));
	}

	public T first() {
		return this.first;
	}

	public T second() {
		return this.second;
	}
}
