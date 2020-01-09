package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 21:37
 **/
public interface Message {

    Class<? extends Message> getType();
}
