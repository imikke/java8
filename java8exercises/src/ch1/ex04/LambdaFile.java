package ch1.ex04;

import java.io.File;
import java.util.ArrayList;

/**
 * Ch1-4
 *
 */
public class LambdaFile {
	// This method sorts as File name.
	// Directories are listed upper than files.
	public File[] sortFileName(File[] files) {
		ArrayList<File> dirsList = new ArrayList<File>();
		ArrayList<File> filesList = new ArrayList<File>();
		ArrayList<File> mergedList = new ArrayList<File>();

		for (File file : files) {
			if (file.isDirectory()) {
				dirsList.add(file);
			} else {
				filesList.add(file);
			}
		}
		dirsList.sort((first, second) -> first.compareTo(second));
		filesList.sort((first, second) -> first.compareTo(second));
		mergedList.addAll(dirsList);
		mergedList.addAll(filesList);
		return mergedList.toArray(new File[mergedList.size()]);
	}
}
