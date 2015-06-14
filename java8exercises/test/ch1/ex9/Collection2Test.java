package ch1.ex9;

import org.junit.Test;

public class Collection2Test {

	@Test
	public void test() {
		String addrs[] = { "NewYork", "Tokyo", "Osaka" };
		ArrayListEx<String> lists = new ArrayListEx<>();
		for (String addr : addrs)
			lists.add(addr);
		lists.forEachIf(string -> System.out.println(string),
				string -> string != null);
	}
}
