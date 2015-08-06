package ch3.ex18;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

import org.junit.Test;

public class ExceptionExTest {

	@Test
	public void test() {
		String path = "/etc/passwd";
		Function<String, String> f = ExceptionEx.unchecked((s) -> new String(
				Files.readAllBytes(Paths.get(s)), StandardCharsets.UTF_8));
		System.out.println(f.apply(path));
	}

}
