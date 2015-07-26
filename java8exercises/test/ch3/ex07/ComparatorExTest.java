package ch3.ex07;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class ComparatorExTest {

	@Test
	public void test() {
		for (ComparatorOption opt : ComparatorOption.values()) {
			String[] words = { "tokyo", " ", "NewYork", "New York", "osaka",
					"Paris", "los angels" };
			try {
				Comparator<String> comp = ComparatorEx.compStrGenerator(opt);
				Arrays.sort(words, comp);
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("---" + opt + "---");
			for (String word : words) {
				System.out.println(word);
			}
		}
		for (ComparatorOption opt1 : ComparatorOption.values()) {
			for (ComparatorOption opt2 : ComparatorOption.values()) {
				for (ComparatorOption opt3 : ComparatorOption.values()) {
					String[] words = { "tokyo", " ", "NewYork", "New York",
							"osaka", "Paris", "los angels" };
					try {
						Comparator<String> comp = ComparatorEx
								.compStrGenerator(opt1, opt2, opt3);
						Arrays.sort(words, comp);
					} catch (Exception e) {
						System.out.println("---" + opt1 + " & " + opt2 + " & "
								+ opt3 + "---");
						System.out.println(e);
						break;
					}
					System.out.println("---" + opt1 + " & " + opt2 + " & "
							+ opt3 + "---");
					for (String word : words) {
						System.out.println(word);
					}
				}
			}
		}
	}
	/*
	 * ---ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---REVERSE_ORDER---
	 * tokyo
	 * osaka
	 * los angels
	 * Paris
	 * NewYork
	 * New York
	 * 
	 * ---CASE_SENSITIVE---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---CASE_INSENSITIVE---
	 * 
	 * los angels
	 * New York
	 * NewYork
	 * osaka
	 * Paris
	 * tokyo
	 * ---UNIGNORE_SPACE---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---IGNORE_SPACE---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---ORDER & ORDER & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---ORDER & ORDER & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---ORDER & REVERSE_ORDER & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---ORDER & CASE_SENSITIVE & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---ORDER & CASE_SENSITIVE & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---ORDER & CASE_INSENSITIVE & ORDER---
	 * 
	 * los angels
	 * New York
	 * NewYork
	 * osaka
	 * Paris
	 * tokyo
	 * ---ORDER & CASE_INSENSITIVE & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---ORDER & UNIGNORE_SPACE & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---ORDER & UNIGNORE_SPACE & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---ORDER & IGNORE_SPACE & ORDER---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---ORDER & IGNORE_SPACE & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---REVERSE_ORDER & ORDER & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---REVERSE_ORDER & REVERSE_ORDER & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---REVERSE_ORDER & CASE_SENSITIVE & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---REVERSE_ORDER & CASE_INSENSITIVE & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---REVERSE_ORDER & UNIGNORE_SPACE & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---REVERSE_ORDER & IGNORE_SPACE & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_SENSITIVE & ORDER & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---CASE_SENSITIVE & ORDER & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_SENSITIVE & REVERSE_ORDER & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_SENSITIVE & CASE_SENSITIVE & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---CASE_SENSITIVE & CASE_SENSITIVE & REVERSE_ORDER---
	 * tokyo
	 * osaka
	 * los angels
	 * Paris
	 * NewYork
	 * New York
	 * 
	 * ---CASE_SENSITIVE & CASE_SENSITIVE & CASE_SENSITIVE---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---CASE_SENSITIVE & CASE_SENSITIVE & CASE_INSENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_SENSITIVE & CASE_INSENSITIVE & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_SENSITIVE & UNIGNORE_SPACE & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---CASE_SENSITIVE & UNIGNORE_SPACE & REVERSE_ORDER---
	 * tokyo
	 * osaka
	 * los angels
	 * Paris
	 * NewYork
	 * New York
	 * 
	 * ---CASE_SENSITIVE & UNIGNORE_SPACE & CASE_SENSITIVE---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---CASE_SENSITIVE & UNIGNORE_SPACE & CASE_INSENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_SENSITIVE & IGNORE_SPACE & ORDER---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---CASE_SENSITIVE & IGNORE_SPACE & REVERSE_ORDER---
	 * tokyo
	 * osaka
	 * los angels
	 * Paris
	 * NewYork
	 * New York
	 * 
	 * ---CASE_SENSITIVE & IGNORE_SPACE & CASE_SENSITIVE---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---CASE_SENSITIVE & IGNORE_SPACE & CASE_INSENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_INSENSITIVE & ORDER & ORDER---
	 * 
	 * los angels
	 * New York
	 * NewYork
	 * osaka
	 * Paris
	 * tokyo
	 * ---CASE_INSENSITIVE & ORDER & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_INSENSITIVE & REVERSE_ORDER & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_INSENSITIVE & CASE_SENSITIVE & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_INSENSITIVE & CASE_INSENSITIVE & ORDER---
	 * 
	 * los angels
	 * New York
	 * NewYork
	 * osaka
	 * Paris
	 * tokyo
	 * ---CASE_INSENSITIVE & CASE_INSENSITIVE & REVERSE_ORDER---
	 * tokyo
	 * Paris
	 * osaka
	 * NewYork
	 * New York
	 * los angels
	 * 
	 * ---CASE_INSENSITIVE & CASE_INSENSITIVE & CASE_SENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_INSENSITIVE & UNIGNORE_SPACE & ORDER---
	 * 
	 * los angels
	 * New York
	 * NewYork
	 * osaka
	 * Paris
	 * tokyo
	 * ---CASE_INSENSITIVE & UNIGNORE_SPACE & REVERSE_ORDER---
	 * tokyo
	 * Paris
	 * osaka
	 * NewYork
	 * New York
	 * los angels
	 * 
	 * ---CASE_INSENSITIVE & UNIGNORE_SPACE & CASE_SENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---CASE_INSENSITIVE & IGNORE_SPACE & ORDER---
	 * 
	 * los angels
	 * New York
	 * NewYork
	 * osaka
	 * Paris
	 * tokyo
	 * ---CASE_INSENSITIVE & IGNORE_SPACE & REVERSE_ORDER---
	 * tokyo
	 * Paris
	 * osaka
	 * NewYork
	 * New York
	 * los angels
	 * 
	 * ---CASE_INSENSITIVE & IGNORE_SPACE & CASE_SENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---UNIGNORE_SPACE & ORDER & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---UNIGNORE_SPACE & ORDER & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---UNIGNORE_SPACE & REVERSE_ORDER & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---UNIGNORE_SPACE & CASE_SENSITIVE & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---UNIGNORE_SPACE & CASE_SENSITIVE & REVERSE_ORDER---
	 * tokyo
	 * osaka
	 * los angels
	 * Paris
	 * NewYork
	 * New York
	 * 
	 * ---UNIGNORE_SPACE & CASE_SENSITIVE & CASE_SENSITIVE---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---UNIGNORE_SPACE & CASE_SENSITIVE & CASE_INSENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---UNIGNORE_SPACE & CASE_INSENSITIVE & ORDER---
	 * 
	 * los angels
	 * New York
	 * NewYork
	 * osaka
	 * Paris
	 * tokyo
	 * ---UNIGNORE_SPACE & CASE_INSENSITIVE & REVERSE_ORDER---
	 * tokyo
	 * Paris
	 * osaka
	 * NewYork
	 * New York
	 * los angels
	 * 
	 * ---UNIGNORE_SPACE & CASE_INSENSITIVE & CASE_SENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---UNIGNORE_SPACE & UNIGNORE_SPACE & ORDER---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---UNIGNORE_SPACE & UNIGNORE_SPACE & REVERSE_ORDER---
	 * tokyo
	 * osaka
	 * los angels
	 * Paris
	 * NewYork
	 * New York
	 * 
	 * ---UNIGNORE_SPACE & UNIGNORE_SPACE & CASE_SENSITIVE---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---UNIGNORE_SPACE & UNIGNORE_SPACE & CASE_INSENSITIVE---
	 * 
	 * los angels
	 * New York
	 * NewYork
	 * osaka
	 * Paris
	 * tokyo
	 * ---UNIGNORE_SPACE & UNIGNORE_SPACE & UNIGNORE_SPACE---
	 * 
	 * New York
	 * NewYork
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---UNIGNORE_SPACE & UNIGNORE_SPACE & IGNORE_SPACE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---UNIGNORE_SPACE & IGNORE_SPACE & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---IGNORE_SPACE & ORDER & ORDER---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---IGNORE_SPACE & ORDER & REVERSE_ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---IGNORE_SPACE & REVERSE_ORDER & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---IGNORE_SPACE & CASE_SENSITIVE & ORDER---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---IGNORE_SPACE & CASE_SENSITIVE & REVERSE_ORDER---
	 * tokyo
	 * osaka
	 * los angels
	 * Paris
	 * NewYork
	 * New York
	 * 
	 * ---IGNORE_SPACE & CASE_SENSITIVE & CASE_SENSITIVE---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---IGNORE_SPACE & CASE_SENSITIVE & CASE_INSENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---IGNORE_SPACE & CASE_INSENSITIVE & ORDER---
	 * 
	 * los angels
	 * NewYork
	 * New York
	 * osaka
	 * Paris
	 * tokyo
	 * ---IGNORE_SPACE & CASE_INSENSITIVE & REVERSE_ORDER---
	 * tokyo
	 * Paris
	 * osaka
	 * NewYork
	 * New York
	 * los angels
	 * 
	 * ---IGNORE_SPACE & CASE_INSENSITIVE & CASE_SENSITIVE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---IGNORE_SPACE & UNIGNORE_SPACE & ORDER---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 * ---IGNORE_SPACE & IGNORE_SPACE & ORDER---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---IGNORE_SPACE & IGNORE_SPACE & REVERSE_ORDER---
	 * tokyo
	 * osaka
	 * los angels
	 * Paris
	 * NewYork
	 * New York
	 * 
	 * ---IGNORE_SPACE & IGNORE_SPACE & CASE_SENSITIVE---
	 * 
	 * NewYork
	 * New York
	 * Paris
	 * los angels
	 * osaka
	 * tokyo
	 * ---IGNORE_SPACE & IGNORE_SPACE & CASE_INSENSITIVE---
	 * 
	 * los angels
	 * NewYork
	 * New York
	 * osaka
	 * Paris
	 * tokyo
	 * ---IGNORE_SPACE & IGNORE_SPACE & UNIGNORE_SPACE---
	 * java.lang.IllegalArgumentException: Those options are not allowed.
	 */

}
