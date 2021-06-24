package aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by qincasin on 2021/6/23.
 * 主线程控制子线程同时开启，主线程再去等待子线程结束
 */
public class CountDownLatchTest2 {

    public static void main(String[] args) throws Exception{
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(i,start,done)).start();
        }
        //这里让主线程休眠500毫秒  确保所有子线程赢启动，并且阻塞在start栅栏处
        TimeUnit.MILLISECONDS.sleep(500);


        System.out.println("子任务栅栏已开启.....");
        start.countDown();


        System.out.println("等待子任务结束.....");
        long before = System.currentTimeMillis();
        done.await();
        long after = System.currentTimeMillis();
        System.out.println("所有子任务已经运行结束，耗时: " + (after - before));

    }

     static class Worker implements Runnable {
        private CountDownLatch start;
        private CountDownLatch done ;
        private int i;

        @Override
        public void run() {
            //为了让所有线程同时开启任务，我们让所有线程先阻塞在这里
            //等大家都准备好了，再打开这个门栓
            try {
                start.await();
                System.out.println("子任务-"+ i +" 开启时间: "+ System.currentTimeMillis());
                doWork();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                done.countDown();
            }


        }

        private void doWork() throws InterruptedException {
            TimeUnit.SECONDS.sleep(5);
        }

        public Worker(int i, CountDownLatch start, CountDownLatch done) {
            this.i =i;
            this.start = start;
            this.done = done;
        }
    }
}
