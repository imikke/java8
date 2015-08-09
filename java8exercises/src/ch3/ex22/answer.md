## 回答

* T->CompletionStage<U>関数（※）を引数に持つthenCompose()がCompletableFutureに対するflatMap操作である。
（※）CompletionStageはinterfaceで、CompletableFutureで実装されている。