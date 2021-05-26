package cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qincasin on 2021/5/22.
 * 模拟访问量  100个用户访问网站 每个网站请求10次
 */
public class Demo2 {
    static int count=0;

    public synchronized static void request() throws InterruptedException {
//        TimeUnit.MILLISECONDS.sleep(5);
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        long start = System.currentTimeMillis();

        System.out.println("开始时间:" + start);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 模拟每个用户访问10次请求
                        for (int i1 = 0; i1 < 10; i1++) {
                            request();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("结束时间" + end);
        System.out.println("总共耗时:" + (end - start) + " 当前数量:" + count);
    }

}
