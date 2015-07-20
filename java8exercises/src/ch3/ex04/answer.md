## 質問

(1)Filterを含む関数型インタフェースが、JavaAPIにいくつあるか。

(2)そのうちのどれが`Predicate<T>`よりも付加価値があるか。

## 回答(1)

* 以下の3つのパッケージ、4つのAPIで使われている。

### java.io

* FileFilter
* FilenameFilter

### java.nio.file

* DirectoryStream.Filter<T>

### java.util.logging

* Filter

### 参考
https://docs.oracle.com/javase/8/docs/api/java/lang/class-use/FunctionalInterface.html

##回答(2)

* 以下の関数は`Predicate<T>`よりも付加価値がある。

### java.io

* FilenameFilter : 指定されたディレクトリ内のファイルリストを作って、その中からファイル名をフィルタする2つの機能を持っているため。