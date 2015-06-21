## 回答

* 古いコードの中にCollectionインタフェースとstream()を持つスーパーインタフェースの2つを拡張しているインタフェースがあった際に、
Collectionインタフェースでデフォルトメソッドstream()が追加されるとコンパイルエラーとなる。
検証として、スーパーインタフェースIとJ、それらを拡張したインタフェースKを用意した。
Jはf()を持ち、この時点ではコンパイルエラーにはなら無いが、Iにデフォルトメソッドf()を追加するとコンパイルエラーとなった。

* バイナリ互換性の確認として、Collectionインタフェースを実装しているArrayListを使ったCollectionImpl.javaをjdk1.7でコンパイルして、class（バイナリ）ファイルをjdk1.8環境で実行した結果、jdk1.7と同様の結果を取得できた。

```
$ java ch1.ex12.CollectionImpl
test1
test2
test3
```

* jarファイルの確認として、上記のCollectionImpl.javaのjarファイルをjdk1.7環境で作成して、そのjarファイルをjdk1.8環境で実行した結果、jdk1.7と同様の結果を取得できた。

```
$ java -jar CollectionImpl.jar 
test1
test2
test3
```