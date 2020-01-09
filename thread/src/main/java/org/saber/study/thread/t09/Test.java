package org.saber.study.thread.t09;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/2 22:11
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Latch latch = new CountDownLatch(3, () -> {
            System.out.println("所有人一起吃野餐");
        });
        new ProgrammerTravel(latch, "张三", "公交车").start();
        new ProgrammerTravel(latch, "李四", "地铁").start();
        new ProgrammerTravel(latch, "王五", "高铁").start();
        latch.await();
    }

}
