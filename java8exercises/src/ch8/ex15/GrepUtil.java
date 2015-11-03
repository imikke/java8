package ch8.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class GrepUtil {

	public static void grep(Path filePath, String pattern) {
		try (Stream<String> lines = Files.lines(filePath)) {
			lines.filter(Pattern.compile(pattern).asPredicate()).forEach(s -> {
				System.out.println(filePath + ": " + s);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void grepDir(Path dirPath, String pattern) {
		try (Stream<Path> entries = Files.walk(dirPath)) {
			entries.filter(p -> !Files.isDirectory(p)).forEach(
					s -> grep(s, pattern));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GrepUtil.grep(Paths.get("test/words.txt"), "java");
		GrepUtil.grepDir(Paths.get("test"), "\\d{5,10}"); // ５桁以上10桁以下の半角数字
	}
}
