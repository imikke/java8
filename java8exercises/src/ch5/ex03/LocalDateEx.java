package ch5.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;
import java.util.function.Predicate;

public class LocalDateEx {
	/**
	 * nextメソッドは、date引数を満足する翌日の日付を生成するTemporalAdjusterを返す。
	 * 
	 * @param date
	 * @return
	 * @exception NullPointerException
	 *                dateがnullの場合
	 */
	public static TemporalAdjuster next(Predicate<LocalDate> date) {
		Objects.requireNonNull(date, "`date` argument must be non-null.");
		TemporalAdjuster nextWorkDay = TemporalAdjusters.ofDateAdjuster(w -> {
			LocalDate result = w;
			do {
				result = result.plusDays(1);
			} while (!date.test(result));
			return result;
		});

		return nextWorkDay;
	}

}
