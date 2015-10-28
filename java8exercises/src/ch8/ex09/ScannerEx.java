package ch8.ex09;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ScannerEx {

	private static <T> Stream<T> iterate(Scanner scanner, int type) {
		Iterator<T> iter = new Iterator<T>() {
			T nextElement = null;

			@Override
			public boolean hasNext() {
				if (nextElement != null) {
					return true;
				} else {
					try {
						if (scanner.hasNext()) {
							// TODO:remove warnings
							switch (type) {
							case 0:
								nextElement = (T) scanner.nextLine();
								break;
							case 1:
								nextElement = (T) scanner.next();
								break;
							case 2:
								nextElement = (T) Integer.valueOf(scanner
										.nextInt());
								break;
							case 3:
								nextElement = (T) Double.valueOf(scanner
										.nextDouble());
								break;
							default:
								assert false;
								break;
							}
						}
						return (nextElement != null);
					} catch (Exception e) {
						throw new NoSuchElementException();
					}
				}
			}

			@Override
			public T next() {
				if (nextElement != null || hasNext()) {
					T word = nextElement;
					nextElement = null;
					return word;
				} else {
					throw new NoSuchElementException();
				}
			}
		};
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED
						| Spliterator.NONNULL), false);

	}

	/**
	 * linesメソッドは、Scannerオブジェクトを行（String型）のストリームに変換する。
	 * 
	 * @param scanner
	 * @return NullPointerException
	 *         scannerがnullの場合
	 */
	public static Stream<String> lines(Scanner scanner) {
		Objects.requireNonNull(scanner, "`scanner` argument must be non-null.");
		return iterate(scanner, 0);
	}

	/**
	 * wordsメソッドは、Scannerオブジェクトを単語（String型）のストリームに変換する。
	 * 
	 * @param scanner
	 * @return NullPointerException
	 *         scannerがnullの場合
	 */
	public static Stream<String> words(Scanner scanner, String delimiter) {
		Objects.requireNonNull(scanner, "`scanner` argument must be non-null.");
		scanner.useDelimiter(delimiter);
		return iterate(scanner, 1);
	}

	/**
	 * integersメソッドは、ScannerオブジェクトをInteger型のストリームに変換する。
	 * 
	 * @param scanner
	 * @return NullPointerException
	 *         scannerがnullの場合
	 */
	public static Stream<Integer> integers(Scanner scanner, String delimiter) {
		Objects.requireNonNull(scanner, "`scanner` argument must be non-null.");
		scanner.useDelimiter(delimiter);
		return iterate(scanner, 2);
	}

	/**
	 * doublesメソッドは、ScannerオブジェクトをDouble型のストリームに変換する。
	 * 
	 * @param scanner
	 * @return NullPointerException
	 *         scannerがnullの場合
	 */
	public static Stream<Double> doubles(Scanner scanner, String delimiter) {
		Objects.requireNonNull(scanner, "`scanner` argument must be non-null.");
		scanner.useDelimiter(delimiter);
		return iterate(scanner, 3);
	}
}
