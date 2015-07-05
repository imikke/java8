package ch1.ex06;

/**
 * Unckeckingクラス
 *
 */
public class Unckecking {

	/**
	 * unckeckメソッドは、RunnableEx.run()でスローされる例外をキャッチして、Runnableインスタンスを返す。
	 * 
	 * @param runner
	 * @return
	 */
	public static Runnable uncheck(RunnableEx runner) {
		Runnable r = () -> {
			try {
				runner.run();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		};
		return r;
	}
}
