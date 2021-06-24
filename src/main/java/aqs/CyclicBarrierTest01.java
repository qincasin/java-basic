package aqs;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by qincasin on 2021/6/24.
 * 测试加载游戏进度，几个英雄进入游戏之前需要全部都加载完毕才能开始战斗
 */
public class CyclicBarrierTest01 {
    public static void main(String[] args) {

        String[] heros = {"安其拉","亚瑟","马超","张飞","刘备"};

        ExecutorService service = Executors.newFixedThreadPool(5);

        CyclicBarrier barrier = new CyclicBarrier(5);

        for (int i = 0; i < 5; i++) {
            service.execute(new Player(heros[i],barrier));
        }

        service.shutdown();

    }

    private static class Player implements Runnable {
        private String hero;
        private CyclicBarrier barrier;
        public Player(String hero, CyclicBarrier barrier) {
            this.hero =hero;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                 //每个玩家加载进度不一样，这里使用随机数来模拟！
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                System.out.println(hero + ": 加载进度100%。等待其他玩家加载完成中...... ");
                barrier.await();
                System.out.println(hero + ": 发现所有英雄加载完毕,开始战斗吧!");

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
