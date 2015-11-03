package ch9.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FilesEx {

	/**
	 * backwordWriteメソッドは、byte配列を逆順に書き出す。
	 * 
	 * @param path
	 * @param bytes
	 * @throws IOException
	 * @exception NullPointerException
	 *                pathがnullの場合
	 */
	public static void backwardWrite(Path path, byte[] bytes)
			throws IOException {
		Objects.requireNonNull(path, "`path` argument must be non-null.");
		byte[] bwBytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			bwBytes[i] = bytes[bytes.length - 1 - i];
		}
		Files.write(path, bwBytes);
	}
}
