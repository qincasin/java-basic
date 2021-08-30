package aqs.barry;

import aqs.ParentInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by qincasin on 2021/7/19.
 */
public class Son0 implements ParentInterface ,Runnable{
    private String name;
    private CyclicBarrier barrier;

    public Son0(String name, CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doSomeThing(name);
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String doSomeThing(String name) {
        String msg = name +" 完成！";
        System.out.println(LocalDateTime.now()+ " " + msg);
        return name+" 完成!";
    }
}
