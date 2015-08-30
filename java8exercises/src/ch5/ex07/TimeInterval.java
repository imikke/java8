package ch5.ex07;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TimeInterval {
	private LocalDate startDay;
	private LocalDate endDay;
	private LocalTime startTime;
	private LocalTime endTime;

	public TimeInterval(LocalDate startDay, LocalTime startTime,
			LocalDate endDay, LocalTime endTime) {
		Objects.requireNonNull(startDay,
				"`startDay` argument must be non-null.");
		Objects.requireNonNull(endDay, "`endDay` argument must be non-null.");
		Objects.requireNonNull(startTime,
				"`startTime` argument must be non-null.");
		Objects.requireNonNull(endTime, "`endTime` argument must be non-null.");

		if (startDay.isAfter(endDay)) {
			throw new IllegalArgumentException(
					"`startDay` argument is later than `endDay` argument.");
		}

		this.startDay = startDay;
		this.endDay = endDay;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * conflictメソッドは、2つのTimeIntervalオブジェクトが重なるかを検査する。
	 * 利便性を考えて、時刻の終了時刻は指定されたものよりも１分短く計算される。
	 * よって、日付が同じの2つのオブジェクトの終了時刻が8:00-9:00と9:00-10:00の場合は重ならないと判断する。
	 * 
	 * @param event1
	 * @param event2
	 * @return
	 * @exception NullPointerException
	 *                event1もしくはevent2がnullの場合
	 */
	public static boolean conflict(TimeInterval event1, TimeInterval event2) {
		Objects.requireNonNull(event1, "`event1` argument must be non-null.");
		Objects.requireNonNull(event2, "`event2` argument must be non-null.");
		// 日付が重なるかを検査
		if ((event1.startDay.isAfter(event2.startDay) && event1.startDay
				.isAfter(event2.endDay))
				|| (event1.endDay.isBefore(event2.startDay) && event1.endDay
						.isBefore(event2.endDay))) {
			return false;
		}
		// 時刻が重なるかを検査
		if ((event1.startTime.isAfter(event2.startTime) && event1.startTime
				.isAfter(event2.endTime.minusMinutes(1)))
				|| (event1.endTime.minusMinutes(1).isBefore(event2.startTime) && event1.endTime
						.minusMinutes(1).isBefore(event2.endTime))) {
			return false;
		}

		return true;
	}
}
