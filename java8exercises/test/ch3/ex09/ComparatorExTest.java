package ch3.ex09;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class ComparatorExTest {

	@Test
	public void test() {
		Person[] people = { new Person("Taro", "Yamada"),
				new Person("Hanako", "Yamada"), new Person("Taro", "Suzuki"),
				new Person("Taro", "Yamada") };
		try {
			Comparator<Person> comp = ComparatorEx.lexicographicComparator(
					"lastName", "firstName");
			Arrays.sort(people, comp);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		for (Person p : people) {
			System.out.println(p.getName());
		}
		/*
		 * lastname, firstnameの順で比較されている。
		 * 
		 * Taro Suzuki
		 * Hanako Yamada
		 * Taro Yamada
		 * Taro Yamada
		 */
	}
}
