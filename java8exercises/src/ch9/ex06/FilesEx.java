package ch9.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilesEx {
	/**
	 * backwordWriteメソッドは、読み込んだ行（List<String>）を逆順に書き出す。
	 * 
	 * @param path
	 * @param bytes
	 * @throws IOException
	 * @exception NullPointerException
	 *                pathがnullの場合
	 */
	public static void backwardWrite(Path path, List<String> list)
			throws IOException {
		Objects.requireNonNull(path, "`path` argument must be non-null.");
		List<String> bwList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			bwList.add(list.get(list.size() - 1 - i));
		}
		Files.write(path, bwList);
	}
}
