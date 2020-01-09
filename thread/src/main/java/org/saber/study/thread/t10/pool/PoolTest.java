package org.saber.study.thread.t10.pool;

import org.saber.study.thread.t10.Pool;

import java.util.RandomAccess;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * description:
 * 线程池测试
 *
 * @author: saber
 * @date: 2020/1/4 16:22
 **/
public class PoolTest {

    public static void main(String[] args) {
        ThreadPool pool = new BasicThreadPool();
        IntStream.range(0, 300).forEach((i) -> {
            pool.execute(() -> {
                long startTime = System.nanoTime();
                System.out.println("任务" + i + "执行");
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long runTime = System.nanoTime() - startTime;
                System.out.println("任务" + i + "执行完成！处理时间：" + runTime);
            });
        });

    }
}
