package ch1.ex02;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;

import org.junit.Test;

public class LambdaFileTest {

	@Test
	public void test() {
		File dir = new File("test/data");
		LambdaFile lf = new LambdaFile();
		String[] expecteds = { "test/data/abc1", "test/data/sub2" };
		File[] actuals = lf.directories(dir);
		String[] actStrs = new String[actuals.length];
		for (int i = 0; i < actuals.length; i++) {
			actStrs[i] = actuals[i].toString();
		}
		assertArrayEquals(expecteds, actStrs);

	}

}
