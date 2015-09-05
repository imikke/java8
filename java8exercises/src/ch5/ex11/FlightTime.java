package ch5.ex11;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class FlightTime {
	private ZoneId from;
	private ZoneId dest;
	private ZonedDateTime departureTime;
	private ZonedDateTime arrivalTime;
	private final long MAX_NUMBER = 100000;
	private long differentHours = MAX_NUMBER;
	private long flightTime = MAX_NUMBER; // hours

	public FlightTime(ZonedDateTime departureTime, ZoneId dest,
			int flightHours, int flightMinutes) {
		Objects.requireNonNull(departureTime,
				"`departureTime` argument must be non-null.");
		Objects.requireNonNull(dest, "`dest` argument must be non-null.");
		if (flightHours < 0 || flightMinutes < 0) {
			throw new IllegalArgumentException(
					"flightHours and flightMinutes arguments are biggger than 1.");
		}
		this.from = departureTime.getZone();
		this.dest = dest;
		this.departureTime = departureTime;
		this.flightTime = flightHours * 60 + flightMinutes;
		setArrivalTime();
		setDifferentHours();
	}

	public FlightTime(ZonedDateTime departureTime, ZoneId dest,
			LocalTime arrivalTimeOfDest) {
		Objects.requireNonNull(departureTime,
				"`departureTime` argument must be non-null.");
		Objects.requireNonNull(dest, "`dest` argument must be non-null.");
		Objects.requireNonNull(arrivalTimeOfDest,
				"`arrivalTimeOfDest` argument must be non-null.");

		this.from = departureTime.getZone();
		this.dest = dest;
		this.departureTime = departureTime;
		this.flightTime = this.departureTime.withZoneSameInstant(this.dest)
				.toLocalTime().until(arrivalTimeOfDest, ChronoUnit.MINUTES);
		setArrivalTime();
		setDifferentHours();
	}

	private void setArrivalTime() {
		this.arrivalTime = this.departureTime.plusMinutes(this.flightTime);
	}

	private void setDifferentHours() {
		this.differentHours = this.arrivalTime.withZoneSameLocal(this.dest)
				.until(this.arrivalTime, ChronoUnit.HOURS);
	}

	private void setFlightTime() {
		this.flightTime = this.departureTime.until(this.arrivalTime,
				ChronoUnit.MINUTES);
	}

	/**
	 * arrivalTimeメソッドは、出発地のタイムゾーンでの到着時刻を返す。
	 * 
	 * @return
	 */
	public ZonedDateTime arrivalTime() {
		if (this.arrivalTime == null) {
			setArrivalTime();
		}
		return this.arrivalTime;
	}

	/**
	 * arrivalTimeメソッドは、到着地のタイムゾーンでの到着時刻を返す。
	 * 
	 * @return
	 */
	public ZonedDateTime arrivalTimeOfDest() {
		if (this.differentHours == MAX_NUMBER) {
			setDifferentHours();
		}
		return this.arrivalTime.plusHours(differentHours).withZoneSameLocal(
				dest);
	}

	/**
	 * flightTimeメソッドは、飛行時間を分単位で返す。
	 * 
	 * @return
	 */
	public long flightTime() {
		if (this.flightTime == MAX_NUMBER) {
			setFlightTime();
		}
		return this.flightTime;
	}
}
