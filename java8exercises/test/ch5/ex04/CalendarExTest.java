package ch5.ex04;

import org.junit.Test;

public class CalendarExTest {

	@Test
	public void test() {
		CalendarEx.showCalendar(2, 2016);

		/*
		 * Result:
		 * Note: Spaces are removed by eclipse's auto format function.
		 * 3/2013
		 * Mon Tue Wed Thu Fri Sat Sun
		 * 1 2 3
		 * 4 5 6 7 8 9 10
		 * 11 12 13 14 15 16 17
		 * 18 19 20 21 22 23 24
		 * 25 26 27 28 29 30 31
		 * 
		 * leap year
		 * 2/2016
		 * Mon Tue Wed Thu Fri Sat Sun
		 * 1 2 3 4 5 6 7
		 * 8 9 10 11 12 13 14
		 * 15 16 17 18 19 20 21
		 * 22 23 24 25 26 27 28
		 * 29
		 */
	}

}
