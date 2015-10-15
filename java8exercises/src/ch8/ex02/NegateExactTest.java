package ch8.ex02;

public class NegateExactTest {

	public static void main(String[] args) {
		// 符号付きintの最小値が例外となる
		Math.negateExact(Integer.MIN_VALUE);
	}
}
