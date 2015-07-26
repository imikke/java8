package ch3.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public class ComparatorEx {
	/**
	 * lexicographicComparatorメソッドは、オブジェクトの引数のフィールドを順に比較する
	 * Comparator<T>型のインスタンスを返す。
	 * 
	 * @param fieldNames
	 * @return
	 * @exception fieldNamesの各フィールド名が存在しない場合は
	 *                、IllegalArgumentException
	 */
	public static <T> Comparator<T> lexicographicComparator(
			String... fieldNames) {
		return (person1, person2) -> {
			int ret = 0;
			Field f1, f2;

			for (String fieldName : fieldNames) {

				try {
					f1 = person1.getClass().getDeclaredField(fieldName);
					f2 = person2.getClass().getDeclaredField(fieldName);
					f1.setAccessible(true);
					f2.setAccessible(true);
					// String型で比較する
					ret = f1.get(person1).toString()
							.compareTo(f2.get(person2).toString());
				} catch (NoSuchFieldException e) {
					throw new IllegalArgumentException(e);
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException e) {
					e.printStackTrace();
				}

				if (ret != 0)
					break;

			}

			return ret;
		};
	}
}
