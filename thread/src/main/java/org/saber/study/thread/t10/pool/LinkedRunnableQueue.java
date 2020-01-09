package org.saber.study.thread.t10.pool;

import java.util.LinkedList;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/3 11:37
 **/
public class LinkedRunnableQueue implements RunnableQueue {

    private int limit;

    private final DenyPolicy denyPolicy;

    private final ThreadPool threadPool;

    private LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (runnableLinkedList) {
            if (size() > limit) {
                denyPolicy.reject(runnable, threadPool);
                return;
            }
            runnableLinkedList.addLast(runnable);
            runnableLinkedList.notifyAll();
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableLinkedList) {
            while (runnableLinkedList.isEmpty()) {
                try {
                    runnableLinkedList.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            return runnableLinkedList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableLinkedList) {
            return runnableLinkedList.size();
        }
    }
}
