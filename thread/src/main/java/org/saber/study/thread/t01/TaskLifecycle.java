package org.saber.study.thread.t01;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 11:34
 **/
public interface TaskLifecycle<T> {

    /**
     * 任务启动时触发
     *
     * @param thread
     */
    void onStart(Thread thread);

    /**
     * 任务运行时触发
     *
     * @param thread
     */
    void onRunning(Thread thread);

    /**
     * 任务运行结束时触发
     *
     * @param thread
     * @param result 线程返回结果
     */
    void onFinish(Thread thread, T result);

    /**
     * 任务运行异常时触发
     *
     * @param thread
     * @param e
     */
    void onError(Thread thread, Exception e);
}
