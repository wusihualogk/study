package org.saber.study.thread.t13;

import java.util.Map;

/**
 * description:
 * 方法消息
 * 收集每一个接口的方法参数，并提供execute()方法
 *
 * @author: saber
 * @date: 2020/1/7 11:37
 **/
public abstract class MethodMessage {

    protected final Map<String, Object> params;

    protected final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }

    public abstract void execute();

}
