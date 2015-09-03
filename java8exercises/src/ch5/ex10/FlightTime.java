package ch5.ex10;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class FlightTime {
	private ZoneId from;
	private ZoneId dest;
	private ZonedDateTime departureTime;
	private ZonedDateTime arrivalTime;
	private long differentHours;

	public FlightTime(ZonedDateTime departureTime, ZoneId dest,
			int flightHours, int flightMinutes) {
		Objects.requireNonNull(departureTime,
				"`departureTime` argument must be non-null.");
		Objects.requireNonNull(departureTime,
				"`ZoneId` argument must be non-null.");
		if (flightHours < 0 || flightMinutes < 0) {
			throw new IllegalArgumentException(
					"flightHours and flightMinutes arguments are biggger than 1.");
		}
		this.from = departureTime.getZone();
		this.dest = dest;
		this.departureTime = departureTime;
		this.arrivalTime = this.departureTime.plusHours(flightHours)
				.plusMinutes(flightMinutes);
		this.differentHours = this.arrivalTime.withZoneSameLocal(this.dest)
				.until(this.arrivalTime, ChronoUnit.HOURS);
	}

	/**
	 * arrivalTimeメソッドは、出発地のタイムゾーンでの到着時刻を返す。
	 * 
	 * @return
	 */
	public ZonedDateTime arrivalTime() {
		return arrivalTime;
	}

	/**
	 * arrivalTimeメソッドは、到着地のタイムゾーンでの到着時刻を返す。
	 * 
	 * @return
	 */
	public ZonedDateTime arrivalTimeOfDest() {
		return this.arrivalTime.plusHours(differentHours).withZoneSameLocal(
				dest);
	}
}
