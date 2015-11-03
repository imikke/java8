package ch9.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class TryTest {

	public static void main(String[] args) throws Exception {
		// try-with-resources
		try (Scanner in = new Scanner(Paths.get("test/words.txt"));
				PrintWriter out = new PrintWriter("/tmp/out.txt")) {
			while (in.hasNext())
				out.println(in.next().toLowerCase());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// does't use try-with-resources
		Scanner in = null;
		PrintWriter out = null;
		try {
			try {
				in = new Scanner(Paths.get("test/words.txt"));
				out = new PrintWriter("/tmp/out2.txt");
				while (in.hasNext())
					out.println(in.next().toLowerCase());
			} catch (Exception e) {
				throw e;
			} finally {
				in.close(); // closeの例外に上書きされる
				out.close();
			}
		} catch (Exception e) {
			throw e;
		}
		// closeされているか確認
		try {
			// in.hasNext();
			// out.println("test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
