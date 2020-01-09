package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 21:45
 **/
public class Event implements Message {

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
