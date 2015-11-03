package ch8.ex14;

import java.util.Objects;

public class RequireNonNullMsg {

	private String str;

	public RequireNonNullMsg(String str) {
		// strがnullでないならthis.strに代入する
		// nullなら例外
		this.str = Objects.requireNonNull(str, () -> "str must not be null");
		System.out.println(this.str);
	}

	public static void main(String[] args) {
		RequireNonNullMsg msg = new RequireNonNullMsg("test");
		msg = new RequireNonNullMsg(null);
	}

}
