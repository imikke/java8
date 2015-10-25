package ch8.ex06;

import java.util.Comparator;
import java.util.Objects;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Comparing2D {

	/**
	 * comparePoint2Dメソッドは、2つのPoint2Dオブジェクトの座標xとyをそれぞれ比較する。
	 * 
	 * @param p1
	 * @param p2
	 * @return 座標xとyがどちらも等しい場合は0を返す。x、yの順で比較して、大きい場合は1、小さい場合は-1を返す。
	 *         xが等しくない場合、yは比較されない。
	 * @exception NullPointerException
	 *                p1もしくはp2がnullの場合
	 */
	public static int comparePoint2D(Point2D p1, Point2D p2) {
		Objects.requireNonNull(p1, "`p1` argument must be non-null.");
		Objects.requireNonNull(p2, "`p2` argument must be non-null.");
		Comparator<Point2D> comparator = Comparator.comparing(Point2D::getX)
				.thenComparing(Point2D::getY);
		return comparator.compare(p1, p2);
	}

	/**
	 * compareRectangle2Dメソッドは、2つのRectangle2Dオブジェクトのwidthとheightをそれぞれ比較する。
	 * 
	 * @param r1
	 * @param r2
	 * @return 
	 *         widthとheightがどちらも等しい場合は0を返す。width、heightの順で比較して、大きい場合は1、小さい場合は-1を返す
	 *         。widthが等しくない場合、heightは比較されない。
	 * @exception NullPointerException
	 *                r1もしくはr2がnullの場合
	 */
	public static int compareRectangle2D(Rectangle2D r1, Rectangle2D r2) {
		Objects.requireNonNull(r1, "`r1` argument must be non-null.");
		Objects.requireNonNull(r2, "`r2` argument must be non-null.");
		Comparator<Rectangle2D> comparator = Comparator.comparing(
				Rectangle2D::getWidth).thenComparing(Rectangle2D::getHeight);
		return comparator.compare(r1, r2);
	}
}
