package ch9.ex11;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GrepProcess {

	/**
	 * grepメソッドは、再帰的に引数に指定されたpathのディレクトリに対して引数argにマッチする文字列を検索する。
	 * 引数argは、拡張正規表現も使用できる。
	 * 結果は、標準出力に表示される。
	 * 
	 * @param path
	 * @param arg
	 * @throws IOException
	 * @throws InterruptedException
	 * @exception NullPointerException
	 *                pathがnullの場合
	 */
	public static void grepR(Path path, String arg) throws IOException,
			InterruptedException {
		Objects.requireNonNull(path, "`path` argument must be non-null.");
		ProcessBuilder builder = new ProcessBuilder("grep", "-rE", arg,
				path.toString());
		builder.inheritIO();
		Process process = builder.start();
		process.waitFor(1, TimeUnit.MINUTES);
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		GrepProcess.grepR(Paths.get("test"), "^\\d{14,16}$");
	}

}
