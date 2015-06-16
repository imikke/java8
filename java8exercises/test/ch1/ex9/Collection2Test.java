package ch1.ex9;

import org.junit.Test;

public class Collection2Test {

	@Test
	public void test() {
		String addrs[] = { "Yokohama", "NewYork", "Yokohama", "Osaka" };
		ArrayListEx<String> lists = new ArrayListEx<>();
		ArrayListEx<String> outlists = new ArrayListEx<>();
		for (String addr : addrs)
			lists.add(addr);
		lists.forEachIf(string -> {
			// System.out.println(string);
			// addをスレッドセーフで実行できるかは保証されない（ 1.6 変数スコープ参照）
			outlists.add(string);
			}, string -> string.equals("Yokohama"));
		outlists.forEach(System.out::println);
	}
}
