package ch5.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class CalendarEx {
	/**
	 * daysSinceBirthメソッドは、誕生日からの日数を返す。
	 * 
	 * @param birthDay
	 * @exception NullPointerException
	 *                birthDayがnullの場合
	 */
	public static void daysSinceBirth(LocalDate birthDay) {
		Objects.requireNonNull(birthDay,
				"`birthDay` argument must be non-null.");
		LocalDate today = LocalDate.now();
		System.out.println("You have lived "
				+ birthDay.until(today, ChronoUnit.DAYS)
				+ " days you were born!");
	}

}
