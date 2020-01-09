package org.saber.study.thread.t14;

import java.lang.reflect.Method;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 16:49
 **/
public interface EventContext {

    String getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent();
}
