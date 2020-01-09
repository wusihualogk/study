package org.saber.study.thread.t10.pool;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/3 11:53
 **/
public interface ThreadPool {

    void execute(Runnable runnable);

    boolean isShutdown();

    void shutdown();

    LinkedRunnableQueue getRunnableQueue();
}
