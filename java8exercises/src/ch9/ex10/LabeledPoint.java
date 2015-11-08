package ch9.ex10;

import java.util.Objects;

public class LabeledPoint implements Comparable<LabeledPoint> {
	private String label;
	private int x;
	private int y;

	public LabeledPoint(String label, int x, int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		LabeledPoint other = (LabeledPoint) otherObject;

		return Objects.equals(label, other.label) && Objects.equals(x, other.x)
				&& Objects.equals(y, other.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, x, y);
	}

	@Override
	public int compareTo(LabeledPoint otherLP) {
		Objects.requireNonNull(otherLP, "`otherLP` argument must be non-null.");
		if (this.label != null && otherLP.label == null) {
			return 1;
		}
		if (this.label == null && otherLP.label != null) {
			return -1;
		}
		if (this.label != null && otherLP.label != null) {
			int stringDiff = this.label.compareTo(otherLP.label);
			if (stringDiff > 0) {
				return 1;
			}
			if (stringDiff < 0) {
				return -1;
			}
		}
		if (this.x > otherLP.x) {
			return 1;
		}
		if (this.x < otherLP.x) {
			return -1;
		}
		if (this.y > otherLP.y) {
			return 1;
		}
		if (this.y < otherLP.y) {
			return -1;
		}
		return 0;
	}
}
