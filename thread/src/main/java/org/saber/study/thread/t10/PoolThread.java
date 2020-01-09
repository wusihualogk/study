package org.saber.study.thread.t10;

import java.util.LinkedList;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/3 10:06
 **/
public class PoolThread extends Thread {
    private static int threadInitNumber = 0;

    private static synchronized int nextThreadNum() {
        return threadInitNumber++;
    }

    private volatile boolean stop = false;

    private Pool pool;

    public PoolThread(Pool pool) {
        super("PoolThread_" + nextThreadNum());
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            if (stop) {
                return;
            }
            try {
                try {
                    Task task = pool.getTask();
                    try {
                        task.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    wait();
                }
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    public void close() {
        stop = true;
        this.interrupt();
    }

}
