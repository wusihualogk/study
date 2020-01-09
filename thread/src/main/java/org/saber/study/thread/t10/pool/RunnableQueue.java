package org.saber.study.thread.t10.pool;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/3 11:33
 **/
public interface RunnableQueue {

    void execute(Runnable runnable);

    Runnable take() throws InterruptedException;

    int size();

}
