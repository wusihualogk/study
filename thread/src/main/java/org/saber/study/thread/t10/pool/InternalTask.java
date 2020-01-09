package org.saber.study.thread.t10.pool;

/**
 * description:
 * 线程池核心类 根据任务列表处理任务 池中每个线程任务处理类
 *
 * @author: saber
 * @date: 2020/1/4 15:28
 **/
public class InternalTask implements Runnable {

    public final RunnableQueue runnableQueue;

    public volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable runnable = runnableQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupt.");
            }
        }
    }

    public void stop() {
        running = false;
    }
}
