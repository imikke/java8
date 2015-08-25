package ch5.ex02;

import java.time.LocalDate;

public class LeapYear {

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2000, 2, 29).plusYears(1);
		System.out.println("plus one year since 2000-02-29 : "
				+ date.toString());
		date = LocalDate.of(2000, 2, 29).plusYears(4);
		System.out.println("plus four years since 2000-02-29 : "
				+ date.toString());
		date = LocalDate.of(2000, 2, 29);
		for (int i = 0; i < 4; i++)
			date = date.plusYears(1);
		System.out.println("plus one year four times since 2000-02-29 : "
				+ date.toString());
		/*
		 * Result:
		 * plus one year since 2000-02-29 : 2001-02-28
		 * plus four years since 2000-02-29 : 2004-02-29
		 * plus one year four times since 2000-02-29 : 2004-02-28
		 */
	}

}
