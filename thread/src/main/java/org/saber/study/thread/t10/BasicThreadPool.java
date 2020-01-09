package org.saber.study.thread.t10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/3 9:26
 **/
public class BasicThreadPool extends Thread implements Pool {

    /**
     * 最小线程数量
     */
    private final int MIN_THREAD_NUM;

    /**
     * 最大线程数量
     */
    private final int MAX_THREAD_NUM;

    /**
     * 核心线程数量，当任务量减少时，保留活跃线程的数量
     */
    private final int CORE_THREAD_NUM;

    /**
     * 最多任务数量
     */
    private final int MAX_TASK_NUM;

    /**
     * 活跃线程数量
     */
    private int activeNum;

    /**
     * 任务列表
     */
    private LinkedList<Task> queue = new LinkedList<>();

    /**
     * 线程列表
     */
    private LinkedList<PoolThread> threadList = new LinkedList<>();

    public BasicThreadPool() {
        this(1, 3, 6, 500);
    }

    public BasicThreadPool(int minThreadNum, int coreThreadNum, int maxThreadNum, int maxTaskNum) {
        MIN_THREAD_NUM = minThreadNum;
        CORE_THREAD_NUM = coreThreadNum;
        MAX_THREAD_NUM = maxThreadNum;
        MAX_TASK_NUM = maxTaskNum;

        argumentCheck();
        initThreadList();
    }

    private void argumentCheck() {
        if (MIN_THREAD_NUM <= 0 || CORE_THREAD_NUM <= 0 || MAX_THREAD_NUM <= 0 || MAX_TASK_NUM <= 0) {
            throw new IllegalArgumentException("线程池参数异常");
        }
        if (MIN_THREAD_NUM > CORE_THREAD_NUM || CORE_THREAD_NUM > MAX_THREAD_NUM) {
            throw new IllegalArgumentException("线程池参数异常");
        }
    }

    private void initThreadList() {
        for (int i = 0; i < MIN_THREAD_NUM; i++) {
            addThread();
        }
    }

    private synchronized void addThread() {
        threadList.add(new PoolThread(this));
        activeNum++;
    }

    private synchronized void removeThread() {
        threadList.get(0).close();
        threadList.removeFirst();
        activeNum--;
    }

    @Override
    public void run() {
        for (int i = 0; i < threadList.size(); i++) {
            threadList.get(i).start();
        }
        while (true) {
            synchronized (this) {
                if (queue.size() > 0) {
                    addThread();
                    threadList.get(threadList.size() - 1).start();
                }

            }
        }
    }

    public synchronized void addTask(Task task) {
        if (queue.size() >= MAX_TASK_NUM) {
            throw new ArrayIndexOutOfBoundsException("超出最多任务数量");
        }
    }

    @Override
    public synchronized Task getTask() {
        if (queue.size() <= 0) {
            throw new ArrayIndexOutOfBoundsException("任务列表为空");
        }
        return queue.removeFirst();
    }

    /**
     * 获取当前线程池活跃线程数量，估计值
     *
     * @return
     */
    public int getActiveNum() {
        return activeNum;
    }
}
