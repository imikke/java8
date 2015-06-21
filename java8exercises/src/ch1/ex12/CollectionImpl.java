package ch1.ex12;

import java.util.ArrayList;

/**
 * CollectionImpleクラスは、jdk1.7環境でバイナリファイル、jarファイルを作る用のクラス。
 * eclipseでは、コンパイラ環境をjdk1.7にするために、別にプロジェクトを作ってそちらでコンパイル、Jarファイルを作成した。
 */
public class CollectionImpl {

	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		al.add("test1");
		al.add("test2");
		al.add("test3");
		for (String str : al) {
			System.out.println(str);
		}
	}
}
