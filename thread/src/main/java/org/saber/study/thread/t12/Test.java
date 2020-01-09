package org.saber.study.thread.t12;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 10:42
 **/
public class Test {

    public static void main(String[] args) {
        final ProductionChannel channel = new ProductionChannel(5);
        AtomicInteger productionNo = new AtomicInteger();
        IntStream.range(0, 8).forEach(i -> {
            System.out.println(i);
            new Thread(() -> {
                while (true) {
                    channel.offerProduction(new Production(productionNo.getAndIncrement()));
                    try {
                        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }

}
