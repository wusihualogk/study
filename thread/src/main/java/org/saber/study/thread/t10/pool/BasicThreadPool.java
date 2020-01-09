package org.saber.study.thread.t10.pool;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/4 15:38
 **/
public class BasicThreadPool extends Thread implements ThreadPool {

    private final int initSize;

    private final int coreSize;

    private final int maxSize;

    private volatile int active;

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    private volatile boolean stop = false;

    private final LinkedRunnableQueue runnableQueue;

    private final LinkedList<ThreadTask> threadTaskList;

    public BasicThreadPool() {
        this(1, 3, 6, 500, new DenyPolicy.DiscardDenyPolicy(), 1000, TimeUnit.MILLISECONDS);
    }

    public BasicThreadPool(int initSize, int coreSize, int maxSize, int queueSize, DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;

        runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        threadTaskList = new LinkedList<ThreadTask>();
        argumentValid();
        init();
    }

    private void argumentValid() {
        if (initSize <= 0 || coreSize <= 0 || maxSize <= 0) {
            throw new IllegalArgumentException("线程池参数异常");
        }
        if (initSize > coreSize || coreSize > maxSize) {
            throw new IllegalArgumentException("线程池参数异常");
        }
    }

    private void init() {
        start();
        IntStream.range(0, initSize).forEach((i) -> {
            newThread();
        });
    }

    private void newThread() {
        ThreadTask threadTask = new ThreadTask(runnableQueue);
        threadTaskList.addLast(threadTask);
        active++;
        threadTask.start();
    }

    private void removeThread() {
        threadTaskList.removeFirst().internalTask.stop();
        active--;
    }

    @Override
    public void execute(Runnable runnable) {
        runnableQueue.execute(runnable);
    }

    @Override
    public void run() {
        while (!isShutdown() && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                stop = true;
                shutdown();
            }
            synchronized (this) {
                if (runnableQueue.size() > 0) {
                    if (active < maxSize) {
                        newThread();
                    }
                } else {
                    if (active > coreSize) {
                        removeThread();
                    }
                }
            }
        }
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (!isShutdown()) {
                while (threadTaskList.size() > 0) {
                    removeThread();
                }
                stop = true;
            }
        }
    }

    public int getInitSize() {
        return initSize;
    }

    public int getCoreSize() {
        return coreSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getActive() {
        return active;
    }

    public LinkedRunnableQueue getRunnableQueue() {
        return runnableQueue;
    }

    @Override
    public boolean isShutdown() {
        return stop;
    }

    public LinkedList<ThreadTask> getThreadTaskList() {
        return threadTaskList;
    }
}
