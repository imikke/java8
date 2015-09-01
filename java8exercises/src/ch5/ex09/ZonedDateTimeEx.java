package ch5.ex09;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

public class ZonedDateTimeEx {
	/**
	 * getAllメソッドは、サポートされる全てのタイムゾーンをStreamとして返す。
	 * 
	 * @return
	 */
	public static Stream<ZonedDateTime> getAll() {
		Stream<String> zoneIds = ZoneId.getAvailableZoneIds().stream();
		Stream<ZonedDateTime> times = zoneIds.map(s -> ZonedDateTime.now(ZoneId
				.of(s)));
		return times;
	}
}
