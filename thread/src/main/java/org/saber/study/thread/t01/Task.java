package org.saber.study.thread.t01;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 11:40
 **/
@FunctionalInterface
public interface Task<T> {

    /**
     * 任务执行接口，允许有返回值
     *
     * @return
     */
    T call();

}
