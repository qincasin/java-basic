package concurrent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by qincasin on 2021/7/23.
 */
public class CompletableFutureTest {


    public static void main(String[] args) {
        List<String> webPages = Arrays.asList("5", "6", "7");
        System.out.println("当前开始处理 = " + LocalDateTime.now());
        List<CompletableFuture> listFuture = webPages.stream()
                .map(CompletableFutureTest::download)
                .collect(Collectors.toList());
        CompletableFuture<Void> future = CompletableFuture.allOf(listFuture.toArray(new CompletableFuture[0]));
        System.out.println("开始等待= " + LocalDateTime.now());
        future.join();
        System.out.println("当前开始处理完成 = " + LocalDateTime.now());
        return;

        //如果想获取返回值的话 可以使用下面的方法
//        future.thenApply(v->{
//            listFuture.stream().map(a->a.get()).collect(Collectors.toList());
//        })
    }

    private static CompletableFuture download(String webPage) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println("开始休眠时间 = " + webPage + " 当前时间 = " + LocalDateTime.now());

        return CompletableFuture.runAsync(new TestRun(Integer.valueOf(webPage)), executorService);
    }

    static class TestRun implements Runnable {
        private Integer i;

        public TestRun(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                System.out.println("开始休眠时间 = " + i + " 当前时间 = " + LocalDateTime.now());

                TimeUnit.SECONDS.sleep(i);

                System.out.println("开始休眠时间结束 = " + i + " 当前时间 = " + LocalDateTime.now());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
