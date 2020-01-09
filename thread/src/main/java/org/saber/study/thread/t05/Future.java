package org.saber.study.thread.t05;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/24 11:48
 **/
public interface Future<T> {

    T get() throws InterruptedException;

    boolean done();

}
