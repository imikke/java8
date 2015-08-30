package ch5.ex05;

import java.time.LocalDate;

import org.junit.Test;

public class CalendarExTest {

	@Test
	public void test() {
		CalendarEx.daysSinceBirth(LocalDate.of(2000, 1, 1));
	}

}
