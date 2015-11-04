package ch9.ex07;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

public class WebUtilTest {

	@Test
	public void test() {
		try {
			WebUtil.webPageWrite("http://horstmann.com",
					Paths.get("/tmp/ch9-7_out.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
