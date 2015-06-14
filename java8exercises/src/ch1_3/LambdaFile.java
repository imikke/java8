package ch1_3;

import java.io.File;

/**
 * CH1-3 エンクロージングスコープからキャプチャされる変数は拡張子名（ext）
 */
public class LambdaFile {

	public String[] extensions(File dir, String ext) {
		// lambda
		return dir.list((File path, String name) -> name.endsWith(ext));

	}
}
