package org.saber.study.thread.t12;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 10:21
 **/
public class Worker extends Thread {

    private final ProductionChannel productionChannel;

    public Worker(String name, ProductionChannel productionChannel) {
        super(name);
        this.productionChannel = productionChannel;
    }

    @Override
    public void run() {
        while (true) {
            Production production = productionChannel.takeProduction();
            System.out.println(getName() + " 线程获取产品" + production.getProId());
            //产品加工
            production.create();
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
