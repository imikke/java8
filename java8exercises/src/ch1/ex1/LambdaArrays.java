package ch1.ex1;

import java.util.Arrays;
import java.util.Comparator;

/*
 * CH1-1
 * 出力文字を見る限り並行処理されていないので、コンパレータのコードは、sortメソッドと同一スレッドで実行される。
 */

public class LambdaArrays {
	private String[] strings;

	public String[] sortArrays(String[] strs) {
		strings = new String[strs.length];
		for (int i = 0; i < strs.length; i++) {
			strings[i] = strs[i];
		}
		Comparator<String> comp = (String first, String second) -> {
			try {
				Thread.sleep(1000); // 1000ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("comparing");
			if (first.length() < second.length())
				return -1;
			else if (first.length() > second.length())
				return 1;
			else
				return 0;
		};

		Arrays.sort(strings, comp);
		System.out.println("sorted");
		System.out.println(Arrays.toString(strings));
		return strings;
	}
}
