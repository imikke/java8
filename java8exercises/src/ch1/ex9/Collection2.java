package ch1.ex9;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {
	public default void forEachIf(Consumer<T> action, Predicate<T> filter) {
		forEach(t -> {
			if (filter.test(t))
				action.accept(t);
		});
	}
}
