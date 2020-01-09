package org.saber.study.thread.t10.pool;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/4 16:06
 **/
public class ThreadTask extends Thread {

    public final InternalTask internalTask;

    private static int threadInitNumber;

    private static synchronized int nextThreadNum() {
        return threadInitNumber++;
    }

    public ThreadTask(RunnableQueue runnableQueue) {
        super("BasicPoolThread_" + nextThreadNum());
        internalTask = new InternalTask(runnableQueue);
    }

    @Override
    public void run() {
        internalTask.run();
    }
}
