package ch5.ex01;

import java.time.LocalDate;
import java.time.Period;

public class ProgrammersDay {
	/**
	 * getメソッドは、指定した年のProgrammer's dayを返す。
	 * 
	 * @param year
	 * @return LocalDateを返す。
	 * @exception 指定した年が負の場合
	 *                、IllegalArgumentExceptionを返す。
	 */
	public static LocalDate get(int year) {
		if (year < 0) {
			throw new IllegalArgumentException("year is bigger than 1.");
		}
		// plus()を用いて計算する
		return LocalDate.of(year, 1, 1).plus(Period.ofDays(255));
	}
}
