package org.saber.study.thread.t05;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/24 11:47
 **/
@FunctionalInterface
public interface Callback<T> {

    void callback(T result);

}
