package org.saber.study.thread.t09;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * 程序员出行
 *
 * @author: saber
 * @date: 2020/1/2 22:12
 **/
public class ProgrammerTravel extends Thread {

    private final Latch latch;

    private final String programmer;

    private final String transportation;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer + "乘坐" + transportation + "出发");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + "乘坐" + transportation + "到达");
        latch.countDown();
    }
}
