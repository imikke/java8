package ch8.ex13;

import java.lang.reflect.Method;

public class RepeatableAnnotationTest {
	@TestCase(params = "4", expected = "24")
	@TestCase(params = "0", expected = "1")
	/**
	 * @long the returned value
	 */
	public static long factorial(int n) {
		return n <= 1 ? 1 : n * factorial(n - 1);
	}

	// TODO: Use Annotations Processor
	public static void main(String[] args) {
		Class cls = RepeatableAnnotationTest.class;
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			try {
				TestCase[] annotations = method
						.getAnnotationsByType(TestCase.class);
				for (TestCase annotation : annotations) {
					System.out.println(annotation.toString());
					if (annotation.expected().equals(
							method.invoke(null,
									new Integer(annotation.params()))
									.toString())) {
						System.out.println("Success");
					} else {
						System.out.println("Failed");
						throw new Exception("TestCase error");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
