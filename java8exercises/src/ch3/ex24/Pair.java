package ch3.ex24;

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
	public static <T, U> Pair<U> map(Pair<T> pair, Function<T, U> f) {
		return new Pair<U>(f.apply(pair.first), f.apply(pair.second));
	}

	public static <T, U> Pair<U> flatMap(Pair<T> pair, Function<T, Pair<U>> f) {
		Pair<U> pair1 = f.apply(pair.first);
		Pair<U> pair2 = f.apply(pair.second);
		// 要素が3つ以上できるので、Pair<T>に対するflatMapは定義できない。
		return null;
	}

	public T first() {
		return this.first;
	}

	public T second() {
		return this.second;
	}
}
