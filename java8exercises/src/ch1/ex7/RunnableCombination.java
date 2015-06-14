package ch1.ex7;

/**
 * RunnableCombinationクラスは、複数のRunnableの実行を組み合わせて実行するクラスである。
 */
public class RunnableCombination {
	/**
	 * andThenメソッドは、２つのRunnable引数に対して、firstを実行した後に、secondを実行するRunnableを返す。
	 */
	public Runnable andThen(Runnable first, Runnable second) {
		Runnable comb = () -> {
			first.run();
			second.run();
		};
		return comb;
	}
}
