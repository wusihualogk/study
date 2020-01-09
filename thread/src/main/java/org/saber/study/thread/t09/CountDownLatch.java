package org.saber.study.thread.t09;

import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/2 22:06
 **/
public class CountDownLatch extends Latch {

    private Runnable runnable = null;

    public CountDownLatch(int limit) {
        super(limit);
    }

    public CountDownLatch(int limit, Runnable runnable) {
        super(limit);
        this.runnable = runnable;
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            while (limit > 0) {
                wait();
            }
            System.out.println("所有人都已到达");
            if (null != runnable) {
                runnable.run();
            }
        }
    }

    public void await(TimeUnit unit, long time) throws InterruptedException, WaitOutTimeException {
        synchronized (this) {
            if (time <= 0) {
                throw new IllegalArgumentException("The time is invalid.");
            }
            long remainingNanos = unit.toNanos(time);
            final long endNanos = remainingNanos + System.nanoTime();
            while (limit > 0) {
                wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
                if (remainingNanos < 0) {
                    throw new WaitOutTimeException("The wait time over");
                }
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("所有的任务都已完成！");
            }
            limit--;
            notifyAll();
        }
    }

    @Override
    public int getUnArrived() {
        return limit;
    }

}
