package ch2.ex04;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOfTest {
	public static void main(String[] args) throws IOException {
		int values[] = { 1, 4, 9, 16 };
		// int[]のstreamになる
		Stream<int[]> intArrayStream = Stream.of(values);
		// 出力するとアドレス（みたいなもの）が表示される
		intArrayStream.forEach(System.out::println);

		// IntStreamを用いてint型のストリームを取得する
		IntStream intStream = IntStream.of(values);
		// 数字が出力される
		intStream.forEach(System.out::println);
	}
}
