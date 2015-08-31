package ch5.ex08;

import org.junit.Test;

public class ZonedDateTimeExTest {

	@Test
	public void test() {
		ZonedDateTimeEx.getAll().forEach(
				s -> System.out.println(s.getZone() + ":" + s.getOffset()));
	}

}
