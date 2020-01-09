package org.saber.study.thread.t10;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/3 9:26
 **/
public interface Pool {

    void addTask(Task task);

    Task getTask();

}
