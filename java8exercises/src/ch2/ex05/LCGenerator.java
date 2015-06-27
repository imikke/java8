package ch2.ex05;

import java.util.stream.Stream;

/**
 * LCGenerator(Linear Congruential Generator)クラスは、線形合同生成器によって乱数生させる。
 */
public class LCGenerator {

	/**
	 * generateRandoms関数は、Steam<Long>型の乱数の無限ストリームを返す。
	 * 
	 * @param seed
	 * @param a
	 * @param c
	 * @param m
	 * @return
	 */
	public Stream<Long> generateRandoms(long seed, long a, long c, long m) {
		return Stream.iterate(seed, x -> (a * x + c) % m);
	}
}
