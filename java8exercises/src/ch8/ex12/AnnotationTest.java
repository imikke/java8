package ch8.ex12;

import java.lang.reflect.Method;

public class AnnotationTest {
	@TestCase(params = "4", expected = "24")
	/**
	 * @long the returned value
	 */
	public static long factorial(int n) {
		return n <= 1 ? 1 : n * factorial(n - 1);
	}

	public static void main(String[] args) {
		Class cls = AnnotationTest.class;
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			try {
				TestCase annotation = method.getAnnotation(TestCase.class);
				if (annotation != null) {
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
