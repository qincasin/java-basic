package aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by qincasin on 2021/6/23.
 * 主线程等待子线程完成例子
 */
public class CountDownLatchTest {

    private static final int task_count = 8;
    //10个线程
    public static final int thread_core_size = 10;
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(task_count);

        Executor executor = Executors.newFixedThreadPool(thread_core_size);
        for (int i = 0; i < 8; i++) {
            executor.execute(new WorkerRunable(i,countDownLatch));
        }
        System.out.println("主线程等待所有子任务完成....");
        long before = System.currentTimeMillis();
        countDownLatch.await();
        long after = System.currentTimeMillis();

        System.out.println("主线程等待时长:"+(after-before));
    }

    static class WorkerRunable implements Runnable {
        private int taskId;
        private CountDownLatch countDownLatch ;

        @Override
        public void run() {
            doWorker();
        }

        private void doWorker() {
            System.out.println("任务id："+ taskId+"正在执行....");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
            System.out.println("任务id："+ taskId+"结束执行....");
        }

        public WorkerRunable(int taskId, CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
            this.taskId = taskId;
        }
    }

}
