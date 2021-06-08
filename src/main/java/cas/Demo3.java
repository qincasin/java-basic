package cas;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by qincasin on 2021/5/22.
 * 模拟访问量  100个用户访问网站 每个网站请求10次
 */
public class Demo3 {
    volatile static int count =0;
//    LongAdder;
//    HashMap

    /**
     * 通过修改count++ 其中第三步的步骤来实现线程安全
     * 如何解决耗时长的问题？
     * A  count++操作实际上是3步来完成的(jvm执行引擎)
     *  1.获取count的值，记做A: A = count
     *  2.将A值+1，得到B：B=A+1
     *  3.将B值赋值给count
     *  升级第三步的实现
     *      1.获取锁
     *      2.获取一下count最新值 ，记做LV
     *      3.判断LV是否等于A，如果相等，则将B的值赋值给count，并返回true，否则返回false
     *      4.释放锁
     * @throws InterruptedException
     */
    public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        int expectCount; //表示期望值
        while (!compareAndSwap((expectCount = getCount()), expectCount + 1)) {
        }
        count++;
    }

    /** 
     *
     * @param expectCount 期望值count
     * @param newCount 需要给count赋值的新值
     * @return true 成功，false 失败
     */
    public static synchronized boolean compareAndSwap(int expectCount,int newCount){
        // 判断count 当前值是否和期望值 expectCount一致，如果一致，将newCount赋值给count
        if (getCount()==expectCount) {
            return true;
        }
        return false;

    }

    public static int getCount(){
        return count;
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
