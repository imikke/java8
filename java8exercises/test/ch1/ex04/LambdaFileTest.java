package ch1.ex04;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;

import org.junit.Test;

public class LambdaFileTest {

	@Test
	public void test() {
		File dir = new File("test/data");
		LambdaFile lf = new LambdaFile();
		String[] expecteds = { "test/data/abc1", "test/data/sub2",
				"test/data/abc.c", "test/data/abc.java", "test/data/xyz.java" };
		File[] originalList = dir.listFiles();
		File[] actuals = lf.sortFileName(originalList);
		String[] actStrs = new String[actuals.length];
		for (int i = 0; i < actuals.length; i++) {
			actStrs[i] = actuals[i].toString();
		}

		assertArrayEquals(expecteds, actStrs);

	}

}
