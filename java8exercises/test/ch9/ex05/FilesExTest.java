package ch9.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class FilesExTest {

	@Test
	public void test() {
		Path path = Paths.get("test/words.txt");
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(path);
			FilesEx.backwardWrite(Paths.get("/tmp/ch9-5_out.txt"), bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * Results:
		 * $ cat /tmp/ch9-5_out.txt
		 * .ytic eht ni stnaruatser ynam era erehT
		 * 
		 * .tcejorp sesicrexe8avaj ni 2hc rof elif elpmas a si sihT
		 */
	}
}
