package ch5.ex03;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class LocalDateExTest {

	@Test
	public void test() {
		LocalDate today = LocalDate.of(2015, 8, 25); // Wednesday
		LocalDate actual = today.with(LocalDateEx.next(w -> w.getDayOfWeek()
				.getValue() < 6));
		assertEquals("2015-08-26", actual.toString());

		today = LocalDate.of(2015, 8, 28);// Friday
		actual = today.with(LocalDateEx
				.next(w -> w.getDayOfWeek().getValue() < 6));
		// actual = today.with(LocalDateEx.next(null));
		assertEquals("2015-08-31", actual.toString());
	}

}
