package ch1_2;

import java.io.File;

/*
 * CH1-2
 */

public class LambdaFile {
	public static void main(String[] args) {

	}

	public File[] directories(File dir) {
		/*
		 * FileFilter filter = new FileFilter() {
		 * 
		 * @Override public boolean accept(File pathname) { return
		 * pathname.isDirectory(); } }; return dir.listFiles(filter);
		 */

		// lambda
		// return dir.listFiles((File path) -> path.isDirectory());

		// method reference
		return dir.listFiles(File::isDirectory);
	}
}
