package org.saber.study.thread.t01;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 11:30
 **/
public interface Observable {

    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }

    /**
     * 获取当前任务的生命周期状态
     *
     * @return
     */
    Cycle getCycle();

    /**
     * 定义启动线程的方法，主要作用是为了屏蔽Thread的其他方法
     */
    void start();

    /**
     * 定义线程的打断方法，主要作用是为了屏蔽Thread的其他方法
     */
    void interrupt();

}
