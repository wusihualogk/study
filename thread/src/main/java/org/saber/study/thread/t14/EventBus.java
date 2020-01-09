package org.saber.study.thread.t14;

import java.util.concurrent.Executor;

/**
 * description:
 * 事件总线
 *
 * @author: saber
 * @date: 2020/1/8 9:56
 **/
public class EventBus implements Bus {

    private final Registry registry = new Registry();

    /**
     * bus名称
     */
    private String busName;

    private final static String DEFAULT_BUS_NAME = "default";

    private final static String DEFAULT_TOPIC = "default-topic";

    private final Dispatcher dispatcher;

    public EventBus() {
        this(DEFAULT_BUS_NAME);
    }

    public EventBus(String busName) {
        this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public EventBus(EventExceptionHandler eventExceptionHandler) {
        this(DEFAULT_BUS_NAME, eventExceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public EventBus(String busName, EventExceptionHandler eventExceptionHandler, Executor executor) {
        this.busName = busName;
        this.dispatcher = Dispatcher.newDispatcher(executor, eventExceptionHandler);
    }


    @Override
    public void register(Object subscriber) {
        registry.bind(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        registry.unbind(subscriber);
    }

    @Override
    public void post(Object event) {
        post(event, DEFAULT_TOPIC);
    }

    @Override
    public void post(Object event, String topic) {
        System.out.println("开始分发消息：" + event + "，主题：" + topic);
        dispatcher.dispatch(this, registry, event, topic);
    }

    @Override
    public void close() {
        dispatcher.close();
    }

    @Override
    public String getBusName() {
        return busName;
    }
}
