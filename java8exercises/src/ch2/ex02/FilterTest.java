package ch2.ex02;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FilterTest {
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths
				.get("test/words.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		// 3文字以上の単語を最初の10個だけ求める（５個以上あることを確かめる用）
		long count = words.stream().filter(w -> {
			if (w.length() > 3) {
				System.out.println("filter test");
				return true;
			}
			return false;
		}).limit(10).count();
		System.out.println(count);

		// 3文字以上の単語を最初の5個だけ求める
		count = words.stream().filter(w -> {
			if (w.length() > 3) {
				System.out.println("filter test");
				return true;
			}
			return false;
		}).limit(5).count();

		System.out.println(count);
	}
}
