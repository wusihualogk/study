package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 21:38
 **/
public interface Channel<E extends Message> {

    /**
     * 调度Message
     *
     * @param message
     */
    void dispatch(E message);
}
