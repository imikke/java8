package ch9.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class FilesExTest {

	@Test
	public void test() {
		Path path = Paths.get("test/words.txt");
		List<String> list = null;
		try {
			list = Files.readAllLines(path);
			FilesEx.backwardWrite(Paths.get("/tmp/ch9-6_out.txt"), list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
