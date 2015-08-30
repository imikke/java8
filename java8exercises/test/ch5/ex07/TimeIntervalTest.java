package ch5.ex07;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

public class TimeIntervalTest {

	@Test
	public void test() {

		LocalDate startDay = LocalDate.of(2015, 8, 31);
		LocalDate endDay = LocalDate.of(2015, 8, 31);
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(10, 0);

		TimeInterval event1 = new TimeInterval(startDay, startTime, endDay,
				endTime);

		// case1: days are not conflict
		startDay = LocalDate.of(2015, 8, 30);
		endDay = LocalDate.of(2015, 8, 30);
		TimeInterval event2 = new TimeInterval(startDay, startTime, endDay,
				endTime);
		boolean actual = TimeInterval.conflict(event1, event2);
		assertEquals(false, actual);

		// case2: days are conflict
		startDay = LocalDate.of(2015, 8, 30);
		endDay = LocalDate.of(2015, 8, 31);

		event2 = new TimeInterval(startDay, startTime, endDay, endTime);

		actual = TimeInterval.conflict(event1, event2);
		assertEquals(true, actual);

		// case3: days are conflict
		startDay = LocalDate.of(2015, 8, 31);
		endDay = LocalDate.of(2015, 8, 31);
		event2 = new TimeInterval(startDay, startTime, endDay, endTime);
		actual = TimeInterval.conflict(event1, event2);
		assertEquals(true, actual);

		// case4: days are conflict
		startDay = LocalDate.of(2015, 8, 31);
		endDay = LocalDate.of(2015, 9, 1);
		event2 = new TimeInterval(startDay, startTime, endDay, endTime);
		actual = TimeInterval.conflict(event1, event2);
		assertEquals(true, actual);

		// case5: days are not conflict
		startDay = LocalDate.of(2015, 9, 1);
		endDay = LocalDate.of(2015, 9, 1);
		event2 = new TimeInterval(startDay, startTime, endDay, endTime);
		actual = TimeInterval.conflict(event1, event2);
		assertEquals(false, actual);

		// case6: times are not conflict
		startDay = LocalDate.of(2015, 8, 31);
		endDay = LocalDate.of(2015, 8, 31);
		startTime = LocalTime.of(8, 0);
		endTime = LocalTime.of(9, 0);
		event2 = new TimeInterval(startDay, startTime, endDay, endTime);
		actual = TimeInterval.conflict(event1, event2);
		assertEquals(false, actual);

		// case7: times are conflict
		startTime = LocalTime.of(8, 59);
		endTime = LocalTime.of(9, 01);
		event2 = new TimeInterval(startDay, startTime, endDay, endTime);
		actual = TimeInterval.conflict(event1, event2);
		assertEquals(true, actual);

		// case8: times are conflict
		startTime = LocalTime.of(9, 0);
		endTime = LocalTime.of(9, 59);
		event2 = new TimeInterval(startDay, startTime, endDay, endTime);
		actual = TimeInterval.conflict(event1, event2);
		assertEquals(true, actual);

		// case9: times are conflict
		startTime = LocalTime.of(9, 59);
		endTime = LocalTime.of(10, 1);
		event2 = new TimeInterval(startDay, startTime, endDay, endTime);
		actual = TimeInterval.conflict(event1, event2);
		assertEquals(true, actual);

		// case10: times are not conflict
		startTime = LocalTime.of(10, 0);
		endTime = LocalTime.of(11, 0);
		event2 = new TimeInterval(startDay, startTime, endDay, endTime);
		actual = TimeInterval.conflict(event1, event2);
		assertEquals(false, actual);

	}
}
