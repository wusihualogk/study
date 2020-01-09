package org.saber.study.thread.t03;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 15:04
 **/
public interface Lock {

    /**
     * 获取显示锁，没有获得锁的线程陷入阻塞状态
     *
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 释放获取的锁
     */
    void unlock();
}
