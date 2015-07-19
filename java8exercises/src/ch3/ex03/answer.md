## 質問

(1)なぜ、アサーションはライブラリの機能として提供されなかったのか。

(2)java8はライブラリの機能として実装することはできますか。

## 回答

(1)ライブラリにするとassert()する前に、別途if文を書かなければいけないので面倒だと考えられた。

```
if (x < 0) {
	AssertionClass.assert();
}
```

* （参考）https://docs.oracle.com/javase/8/docs/technotes/guides/language/assert.html  
Why does this facility justify a language change, as opposed to a library solution?


(2)java8だとラムダ式を使って、条件式を引数に与えることができるので、(1)の問題は改善される。

```
AssertionClass.assert(() -> x < 0);
```