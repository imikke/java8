package ch1_3;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;

import org.junit.Test;

public class LambdaFileTest {

	@Test
	public void test() {
		File dir = new File("test/data");
		LambdaFile lf = new LambdaFile();
		String[] expecteds = { "abc.java", "xyz.java" };
		String[] actuals = lf.extensions(dir, ".java");

		assertArrayEquals(expecteds, actuals);

	}

}
