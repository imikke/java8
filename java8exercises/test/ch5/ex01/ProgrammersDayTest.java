package ch5.ex01;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class ProgrammersDayTest {

	@Test
	public void test() {
		LocalDate pday = ProgrammersDay.get(2014);
		assertEquals("2014-09-13", pday.toString());

		// leap year
		pday = ProgrammersDay.get(2016);
		assertEquals("2016-09-12", pday.toString());

	}

}
