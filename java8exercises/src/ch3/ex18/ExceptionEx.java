package ch3.ex18;

import java.util.function.Function;

@FunctionalInterface
interface CallableEx<T, V> {
	/**
	 * callメソッドは、引数を受け取って結果を計算する。
	 * 計算ができない場合は例外をスローする。
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	V call(T t) throws Exception;
}

public class ExceptionEx {
	public static <T, R> Function<T, R> unchecked(CallableEx<T, R> f) {
		return (x) -> {
			try {
				return f.call(x);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} catch (Throwable t) {
				throw t;
			}
		};
	}
}
