package org.saber.study.thread.t09;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/2 17:43
 **/
public abstract class Latch {

    protected int limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    /**
     * 使线程阻塞，知道所有线程完成
     *
     * @throws InterruptedException
     */
    public abstract void await() throws InterruptedException;

    /**
     * 计数减一
     */
    public abstract void countDown();

    /**
     * 获取当前还有多少线程未完成，估计值
     *
     * @return
     */
    public abstract int getUnArrived();

}
