package ch9.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class WebUtil {

	/**
	 * webPageWriteメソッドは、読み込んだWeb pageを指定されたファイルに保存する。
	 * ファイルが存在する場合は上書きする。
	 * 
	 * @param urlString
	 * @param filePath
	 * @throws IOException
	 */
	public static void webPageWrite(String urlString, Path filePath)
			throws IOException {
		InputStream in = new URL(urlString).openStream();
		try {
			Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw e;
		}
	}
}
