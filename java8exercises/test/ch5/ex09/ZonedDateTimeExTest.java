package ch5.ex09;

import java.time.temporal.ChronoField;

import org.junit.Test;

public class ZonedDateTimeExTest {

	@Test
	public void test() {
		ZonedDateTimeEx.getAll().filter(s -> {
			int offset = s.getOffset().get(ChronoField.OFFSET_SECONDS);
			return -1 < offset && offset < 1;
		}).forEach(s -> System.out.println(s.getZone() + ":" + s.getOffset()));
		/*
		 * Result:
		 * GMT:Z
		 * Etc/GMT-0:Z
		 * Atlantic/St_Helena:Z
		 * Etc/GMT+0:Z
		 * Africa/Banjul:Z
		 * Etc/GMT:Z
		 * Africa/Freetown:Z
		 * Africa/Bamako:Z
		 * Africa/Conakry:Z
		 * Universal:Z
		 * Africa/Sao_Tome:Z
		 * Africa/Nouakchott:Z
		 * UTC:Z
		 * Etc/Universal:Z
		 * Atlantic/Azores:Z
		 * Africa/Abidjan:Z
		 * Africa/Accra:Z
		 * Etc/UCT:Z
		 * GMT0:Z
		 * Zulu:Z
		 * Africa/Ouagadougou:Z
		 * Atlantic/Reykjavik:Z
		 * Etc/Zulu:Z
		 * Iceland:Z
		 * Africa/Lome:Z
		 * Greenwich:Z
		 * Etc/GMT0:Z
		 * America/Danmarkshavn:Z
		 * Africa/Dakar:Z
		 * America/Scoresbysund:Z
		 * Africa/Bissau:Z
		 * Etc/Greenwich:Z
		 * Africa/Timbuktu:Z
		 * UCT:Z
		 * Africa/Monrovia:Z
		 * Etc/UTC:Z
		 */
	}

}
