package aqs.barry;

import aqs.ParentInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qincasin on 2021/7/19.
 */
public class TestMain {
    static List<ParentInterface>  list = new ArrayList<>();
    public static void main(String[] args) {
        String[] names = new String[]{"0","1","2","3","4"};
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5);
        executorService.execute(new Son0(names[0],barrier));
        executorService.execute(new Son1(names[1],barrier));
        executorService.execute(new Son2(names[2],barrier));
        executorService.execute(new Son3(names[3],barrier));
        executorService.execute(new Son4(names[4],barrier));
        executorService.shutdown();
        System.out.println("全部完成");



    }
}
