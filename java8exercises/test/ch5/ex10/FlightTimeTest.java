package ch5.ex10;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class FlightTimeTest {

	@Test
	public void test() {
		ZoneId from = ZoneId.of("America/Los_Angeles");
		ZoneId dest = ZoneId.of("Europe/Berlin");
		ZonedDateTime departureTime = ZonedDateTime.of(LocalDate.now(),
				LocalTime.of(15, 5), from);
		FlightTime ft = new FlightTime(departureTime, dest, 10, 50);
		// LAとフランクフルトの時差は+９時間
		ZonedDateTime arrivalTime = ft.arrivalTime();
		assertEquals(
				9,
				(int) arrivalTime.toLocalDateTime().until(
						ft.arrivalTimeOfDest().toLocalDateTime(),
						ChronoUnit.HOURS));
		System.out.println("Arrival time : " + arrivalTime);
		System.out.println("Arrival time : " + ft.arrivalTimeOfDest());
	}
}
