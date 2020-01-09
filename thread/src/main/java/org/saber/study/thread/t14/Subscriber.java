package org.saber.study.thread.t14;

import java.lang.reflect.Method;

/**
 * description:
 * 注册者
 *
 * @author: saber
 * @date: 2020/1/8 9:59
 **/
public class Subscriber {

    private final Object subscriberObject;

    private final Method subscriberMethod;

    private boolean disable = false;

    public Subscriber(Object subscriberObject, Method subscriberMethod) {
        this.subscriberObject = subscriberObject;
        this.subscriberMethod = subscriberMethod;
    }

    public Object getSubscriberObject() {
        return subscriberObject;
    }

    public Method getSubscriberMethod() {
        return subscriberMethod;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
