package ch5.ex11;

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
		ZoneId from = ZoneId.of("Europe/Berlin");
		ZoneId dest = ZoneId.of("America/Los_Angeles");
		ZonedDateTime departureTime = ZonedDateTime.of(LocalDate.now(),
				LocalTime.of(14, 5), from);
		LocalTime arrivalTimeOfDest = LocalTime.of(16, 40);
		FlightTime ft = new FlightTime(departureTime, dest, arrivalTimeOfDest);
		// フランクフルト14:05のときLAは05:05、到着時刻は16:40なのでフライト時間は11時間40分
		assertEquals(11, ft.flightTime() / 60); // Hours
		assertEquals(35, ft.flightTime() % 60); // Minutes

		// ch5.ex10で利用したそれぞれの到着時刻で時差を求めるテスト
		from = ZoneId.of("America/Los_Angeles");
		dest = ZoneId.of("Europe/Berlin");
		departureTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(15, 5),
				from);
		ft = new FlightTime(departureTime, dest, 10, 50);
		// LAとフランクフルトの時差は+９時間
		ZonedDateTime arrivalTime = ft.arrivalTime();
		assertEquals(
				9,
				(int) arrivalTime.toLocalDateTime().until(
						ft.arrivalTimeOfDest().toLocalDateTime(),
						ChronoUnit.HOURS));
	}

}
