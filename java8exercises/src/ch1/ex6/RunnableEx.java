package ch1.ex6;

/**
 * RunnableExインタフェースは、Runnable.run()を拡張して、例外をスローする様にしたインタフェースである。
 */
public interface RunnableEx {

	/**
	 * runメソッドは、Runnable.run()とは異なり、例外をスローする。
	 * 
	 * @throws InterruptedException
	 */
	public void run() throws InterruptedException;

}
