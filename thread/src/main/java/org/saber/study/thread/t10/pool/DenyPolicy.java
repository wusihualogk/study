package org.saber.study.thread.t10.pool;

/**
 * description:
 * 否认策略
 *
 * @author: saber
 * @date: 2020/1/3 11:47
 **/
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    /**
     * 丢弃任务
     */
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }

    /**
     * 中止任务
     */
    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("The runnable " + runnable + " will be abort.");
        }
    }

    /**
     * 在添加任务的线程中执行任务本身
     */
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }

}
