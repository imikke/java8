package ch3.ex20;

import java.util.List;
import java.util.function.Function;

public interface ListEx<T> {
	/**
	 * mapメソッドはList<T>の各要素にFunction<T,U> fを適用して、List<U>を作成する。
	 * 
	 * @param list
	 * @param f
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
		List<U> out = null;
		try {
			// TODO:@SuppressWarnings以外にuncheckedの警告が出ない方法が無いか調べる
			out = list.getClass().newInstance();
			for (T t : list) {
				out.add(f.apply(t));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return out;
	}
}
