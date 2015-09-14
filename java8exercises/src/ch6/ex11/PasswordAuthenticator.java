package ch6.ex11;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Util {

	public static Scanner in = new Scanner(System.in);

	// EclipseだとSystem.console()はnullとなるので使えなかった。
	// public static Console console = System.console();
	// public static Scanner in = new Scanner(console.reader());

	public static String getInput(String prompt) {
		System.out.print(prompt + ": ");
		return in.nextLine();
	}

	public static <T> CompletableFuture<T> repeat(Supplier<T> action,
			Predicate<T> until) {
		return CompletableFuture.supplyAsync(action).thenComposeAsync(
				(T t) -> {
					return until.test(t) ? CompletableFuture.completedFuture(t)
							: repeat(action, until);
				});
	}

}

public class PasswordAuthenticator {

	public static void main(String[] args) {
		String user = "user";
		String password = "secret";
		PasswordAuthentication auth = new PasswordAuthentication(user,
				password.toCharArray());

		CompletableFuture<String> result = Util.repeat(
				() -> Util.getInput("Please input password"), (t) -> {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return String.valueOf(auth.getPassword()).equals(t);
				});
		result.thenApply((t) -> {
			System.out.println("OK");
			return "OK";
		});
		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
	}
}
