package cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qincasin on 2021/5/26.
 * 模拟cas aba bug    主线程 将a +1  得到2 ， 其他线程 将a+2 然后在-1   最终结果a 都是 2
 */
public class CasBugDemo {
    public static AtomicInteger a = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread main = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作线程" + Thread.currentThread().getName() + " ,初始值：" + a.get());
                try {
                    int expectNum = a.get();
                    int newNum = expectNum + 1;
                    Thread.sleep(1000);
                    boolean isCasSuccess = a.compareAndSet(expectNum, newNum);
                    System.out.println("操作线程" + Thread.currentThread().getName() + " ,CAS操作：" + isCasSuccess);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "主线程");
        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                    a.incrementAndGet();// a+1,a=2
                    System.out.println("操作线程" + Thread.currentThread().getName() + " ,[incrememt] 值：" + a.get());
                    a.decrementAndGet();  // a-1,a=1
                    System.out.println("操作线程" + Thread.currentThread().getName() + " ,[decrememt] 值：" + a.get());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "干扰线程");
        main.start();
        other.start();
    }
}
