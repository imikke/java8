package ch8.ex16;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AddressAnalyzer {

	/**
	 * analyzeメソッドは、入力されたストリームからcity, state, zipの文字列をマッチしてMatcher型の配列を返す。
	 * 戻り値のMatcher型に対しては、macher.group("city")で、マッチした値を取得できる。（state,zipも同様）
	 * 入力ストリームの形式は以下を想定している。
	 * city, state, zip
	 * ただし、stateは２文字、zipは、5桁もしくは９桁でなければならない。
	 * 
	 * @param input
	 * @return NullPointerException inputがnullの場合
	 */
	public static Matcher[] analyze(Stream<String> input) {
		Objects.requireNonNull(input, "`input` argument must be non-null.");
		String pattern = "(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2}),\\s*(?<zip>[0-9]{5}|[0-9]{9})";
		Stream<Matcher> matchers = input.map(s -> {
			return Pattern.compile(pattern).matcher(s);
		});
		return matchers.toArray(Matcher[]::new);
	}
}
