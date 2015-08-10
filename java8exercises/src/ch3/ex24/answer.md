## 回答

* Pair<T>に対するflatMapを定義できない。なぜなら、例えばPair<Pair<String>>をフラット化すると4つの要素ができてしまい、Pairではなくなってしまう。