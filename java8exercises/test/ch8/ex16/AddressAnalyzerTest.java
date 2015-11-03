package ch8.ex16;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;

import org.junit.Test;

public class AddressAnalyzerTest {

	@Test
	public void test() {
		Path filePath = Paths.get("test/addresses.txt");
		Matcher[] matchers = null;
		try {
			matchers = AddressAnalyzer.analyze(Files.lines(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(5, matchers.length);
		for (Matcher m : matchers) {
			if (m.matches()) {
				System.out.println(m.group("city") + "," + m.group("state")
						+ "," + m.group("zip"));
			}
		}
		/*
		 * Result:
		 * Los Angeles,CA,90001
		 * New York,NY,10001
		 * Honolulu,HI,96801
		 * Haleiwa,HI,967120001
		 */
	}

}
