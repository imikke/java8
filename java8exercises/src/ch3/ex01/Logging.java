package ch3.ex01;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
	public static void info(Logger logger, Supplier<String> message) {
		if (logger.isLoggable(Level.INFO))
			logger.info(message.get());
	}

	/**
	 * logIfメソッドは、条件的なロギングを実施する。
	 * 
	 * @param level
	 *            ログレベル
	 * @param filter
	 *            条件
	 * @param message
	 *            ログメッセージ
	 */
	public static void logIf(Level level, Supplier<Boolean> filter,
			Supplier<String> message) {
		Logger logger = Logger.getGlobal();
		// ロギング可能なレベルあれば、条件を評価する
		if (logger.isLoggable(level) && filter.get()) {
			System.out.println(level.toString() + " is loggable.");
			logger.log(level, message.get());
		}
	}

}
