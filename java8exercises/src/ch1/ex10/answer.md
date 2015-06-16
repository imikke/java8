## 質問
何でもできるとしたら、どのインターフィースにCollectionsクラスの各メソッドを入れるか。

## 回答
メソッドの引数にあるインタフェースにオブジェクトが渡されて、そのオブジェクトに対して何か操作する様なメソッドは、その渡されたインターフェースのデフォルトメソッドに入れる。
オブジェクトが渡されずに、ファクトリーメソッドとなる（オブジェクトを返す）ものに関しては、戻り値の型となるインタフェースのstaticメソッドに入れる。
例えば、binarySearch()は、引数に渡されたListの各要素とKeyを比較して一致したかどうかを返すので、以下の様にListインタフェースのデフォルトメソッドに入れて使用するのが良いと思われる。

ArrayList<String> list = new ArrayList<>();
list.binarySearch("key", (first, second) -> Integer.compare(first.length(), second.length()));

また、emptyList()の様な新しくListを返すものは、Listインタフェースのstaticメソッドに入れるのが良いと思われる。