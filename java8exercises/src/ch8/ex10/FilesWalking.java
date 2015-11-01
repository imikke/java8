package ch8.ex10;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesWalking {

	public static void main(String[] args) throws IOException {
		Path pathToDirectory = Paths.get("/tmp/java_src");
		try (Stream<Path> entries = Files.walk(pathToDirectory)) {
			Stream<Path> paths = entries.filter(p -> {
				String pathJava = p.getFileName().toString();
				long count = 0;
				if (pathJava.endsWith(".java")) {
					try {
						String contents = new String(Files.readAllBytes(p),
								StandardCharsets.UTF_8);
						Stream<String> words = Stream.of(contents
								.split("[\\P{Alnum}]+"));
						count = words.filter(w -> {
							if (w.equals("transient") || w.equals("volatile")) {
								return true;
							}
							return false;
						}).count();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				if (count > 0) {
					return true;
				}
				return false;
			});
			// System.out.println(paths.count()); // 513
			paths.forEach(System.out::println);
		}
		// Result:
		// $ $ find /tmp/java_src -name *.java | xargs egrep -ch
		// '\btransient\b|\bvolatile\b' | awk 'BEGIN{sum=0}{if ($1 > 0)
		// sum=sum+1;}END{print sum}'
		// 513

	}
}
