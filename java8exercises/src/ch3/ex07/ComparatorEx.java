package ch3.ex07;

import java.util.Comparator;

public class ComparatorEx {
	/**
	 * compStrGeneratorメソッドは、引数のオプションに従って、Comparator<String>を返す。
	 * オプションは、CompareOptionに従う。
	 * 
	 * オプションの組み合わせは８パターン
	 * 順、大小区別有、空白除外
	 * 順、大小区別有、空白含む
	 * 順、大小区別無、空白除外
	 * 順、大小区別無、空白含む
	 * 同様に逆順もある
	 * 
	 * @param options
	 * @return
	 * @throw IllegalArgumentException
	 *        引数が0以下4以上、対となるオプション（普通の順序と逆順など）が組み合わせがある場合
	 */
	public static Comparator<String> compStrGenerator(
			ComparatorOption... options) {
		Comparator<String> comp = null;
		comp = (str1, str2) -> {
			int ret = 0;
			// 引数の長さの確認
			if (options.length < 1 || 3 < options.length) {
				throw new IllegalArgumentException(
						"option arguments are more than 3 or less than 1");
			}
			// 対となるオプションが指定されていないか確認
			boolean[] flags = { false, false, false, false, false, false };
			for (ComparatorOption option : options) {
				switch (option) {
				case ORDER:
					flags[0] = true;
					break;
				case REVERSE_ORDER:
					flags[1] = true;
					break;
				case CASE_SENSITIVE:
					flags[2] = true;
					break;
				case CASE_INSENSITIVE:
					flags[3] = true;
					break;
				case UNIGNORE_SPACE:
					flags[4] = true;
					break;
				case IGNORE_SPACE:
					flags[5] = true;
					break;
				default:
					assert false;
					break;
				}
			}
			if ((flags[0] == true && flags[1] == true)
					|| (flags[2] == true && flags[3] == true)
					|| (flags[4] == true && flags[5] == true)) {
				throw new IllegalArgumentException(
						"Those options are not allowed.");
			}

			String str1Tmp = str1;
			String str2Tmp = str2;
			for (ComparatorOption option : options) {
				// 空白確認
				if (option == ComparatorOption.IGNORE_SPACE) {
					str1Tmp = str1.replaceAll(" ", "");
					str2Tmp = str2.replaceAll(" ", "");
				}
				// 大文字小文字確認、比較
				if (option == ComparatorOption.CASE_INSENSITIVE) {
					ret = str1Tmp.compareToIgnoreCase(str2Tmp);
					break;
				} else {
					// ORDER, CASE_SENSITIVE, UNIGNORE_SPACE
					ret = str1Tmp.compareTo(str2Tmp);
				}
			}

			// 逆順
			for (ComparatorOption option : options) {
				if (option == ComparatorOption.REVERSE_ORDER) {
					ret = ret * -1;
				}
			}

			return ret;
		};
		return comp;
	}
}
