package ch1.ex6;

public class Unckecking {

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
