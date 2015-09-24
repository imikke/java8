package ch5.ex04;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class CalendarEx {
	/**
	 * showCalendarメソッドは、与えられた年月のカレンダーを表示する。
	 * 
	 * @param month
	 * @param year
	 * @exception IllegalArgumentException
	 *                指定した年（0以上）、月（１〜12）が有効でない
	 */
	public static void showCalendar(int month, int year) {
		if (month < 1 || month > 12 || year < 0) {
			throw new IllegalArgumentException("arguments are invalid.");
		}
		DayOfWeek firstDayOfWeek = LocalDate.of(year, month, 1).getDayOfWeek();
		LocalDate lastDay = LocalDate.of(year, month, 1).with(
				TemporalAdjusters.lastDayOfMonth());

		// System.out.println(firstDayOfWeek.getValue() + ","
		// + lastDay.getDayOfMonth());
		Integer[] days = new Integer[37]; // 31+6
		int count = 1;

		System.out.printf("%2d/%4d%n", month, year);
		System.out.printf(" Mon Tue Wed Thu Fri Sat Sun%n");
		for (int i = 0; i < days.length; i++) {
			days[i] = 0;
			if (i >= (firstDayOfWeek.getValue() - 1)
					&& count <= lastDay.getDayOfMonth()) {
				days[i] = count;
				count++;
			}

			if (i % 7 == 0 && i != 0) {
				System.out.printf("%n");
			}
			if (days[i] != 0) {
				System.out.printf(" %3d", days[i]);
			} else {
				System.out.printf("    ");
			}
		}

	}
}
