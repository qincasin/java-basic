我们可以基于CompletableFuture创建任务和链式处理多个任务，并实现按照任务完成的先后顺序获取任务的结果。

（1）创建任务

##使用runAsync方法新建一个线程来运行Runnable对象(无返回值)；

##使用supplyAysnc方法新建线程来运行Supplier<T>对象(有返回值)；

##基于线程池创建

（2）任务的异步处理

不论Future.get()方法还是CompletableFuture.get()方法都是阻塞的，为了获取任务的结果同时不阻塞当前线程的执行，我们可以使用CompletionStage提供的方法结合callback来实现任务的异步处理。

##whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
##whenCompleteAsync：把 whenCompleteAsync 这个任务继续提交给线程池来进行执行，也就是并行执行。

##thenApply：当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化

##thenAccept：thenAccept接收上一阶段的输出作为本阶段的输入，并消费处理，无返回结果。　

##thenRun：不关心前一阶段的计算结果，因为它不需要输入参数，进行消费处理，无返回结果。

## thenCombine：会把两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。

## applyToEither ：两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。

##acceptEither 方法：两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作