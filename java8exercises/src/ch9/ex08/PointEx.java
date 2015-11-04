package ch9.ex08;

import java.util.Objects;

public class PointEx {
	private int x, y;

	public PointEx(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	/**
	 * compareToメソッドは、引数のPointExオブジェクトのx、yと比較する。
	 * 
	 * @param other
	 * @return 等しい時は0、otherより大きい時は正の値、小さい時は負の値を返す
	 * @exception NullPointerException
	 *                otherがnullの場合
	 */
	public int compareTo(PointEx other) {
		Objects.requireNonNull(other, "`other` argument must be non-null.");
		int diff = Integer.compare(x, other.getX());
		if (diff != 0)
			return diff;
		return Integer.compare(y, other.getY());
	}
}
