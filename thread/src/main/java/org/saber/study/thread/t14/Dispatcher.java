package org.saber.study.thread.t14;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * description:
 * 分配器
 *
 * @author: saber
 * @date: 2020/1/8 16:42
 **/
public class Dispatcher {

    private final Executor executorService;

    private final EventExceptionHandler eventExceptionHandler;


    public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;

    public static final Executor PRE_EXECUTOR_SERVICE = PreExecutorService.INSTANCE;

    private Dispatcher(Executor executorService, EventExceptionHandler eventExceptionHandler) {
        this.executorService = executorService;
        this.eventExceptionHandler = eventExceptionHandler;
    }

    public void dispatch(Bus bus, Registry registry, Object event, String topic) {
        ConcurrentLinkedQueue<Subscriber> subscriberQueue = registry.scanSubscriber(topic);
        if (subscriberQueue == null) {
            if (eventExceptionHandler != null) {
                eventExceptionHandler.handler(new IllegalArgumentException("topic 没有绑定 subscriber"),
                        new BaseEventContext(bus.getBusName(), null, event));
            }
            return;
        }
        subscriberQueue.stream()
                //未被弃用
                .filter(subscriber -> !subscriber.isDisable())
                //方法第一个参数的类型必须与event的Class相同
                .filter(subscriber -> {
                    Method subscriberMethod = subscriber.getSubscriberMethod();
                    Class<?> aClass = subscriberMethod.getParameterTypes()[0];
                    return aClass.isAssignableFrom(event.getClass());
                })
                .forEach(subscriber -> realInvokeSubscriber(subscriber, event, bus));
    }

    /**
     * 实际调用注册者 分发事件真正执行的内容
     *
     * @param subscriber
     * @param event
     * @param bus
     */
    private void realInvokeSubscriber(Subscriber subscriber, Object event, Bus bus) {
        executorService.execute(() -> {
            try {
                String className = subscriber.getSubscriberMethod().getDeclaringClass().getName();
                String methodName = subscriber.getSubscriberMethod().getName();
                System.out.println(className + "." + methodName + "接收消息，参数：" + event);
                subscriber.getSubscriberMethod().invoke(subscriber.getSubscriberObject(), event);
            } catch (Exception e) {
                if (eventExceptionHandler != null) {
                    eventExceptionHandler.handler(e, new BaseEventContext(bus.getBusName(), subscriber, event));
                }
            }
        });
    }

    /**
     * 关闭，释放资源
     */
    public void close() {
        if (executorService instanceof ExecutorService) {
            ((ExecutorService) executorService).shutdown();
        }
    }

    static Dispatcher newDispatcher(Executor executor, EventExceptionHandler eventExceptionHandler) {
        return new Dispatcher(executor, eventExceptionHandler);
    }

    static Dispatcher seqDisPatcher(EventExceptionHandler eventExceptionHandler) {
        return newDispatcher(SEQ_EXECUTOR_SERVICE, eventExceptionHandler);
    }

    static Dispatcher preDisPatcher(EventExceptionHandler eventExceptionHandler) {
        return newDispatcher(PRE_EXECUTOR_SERVICE, eventExceptionHandler);
    }


    private static class BaseEventContext implements EventContext {

        private final String eventBusName;

        private final Subscriber subscriber;

        private final Object event;

        public BaseEventContext(String eventBusName, Subscriber subscriber, Object event) {
            this.eventBusName = eventBusName;
            this.subscriber = subscriber;
            this.event = event;
        }

        @Override
        public String getSource() {
            return eventBusName;
        }

        @Override
        public Object getSubscriber() {
            return subscriber != null ? subscriber.getSubscriberObject() : null;
        }

        @Override
        public Method getSubscribe() {
            return subscriber != null ? subscriber.getSubscriberMethod() : null;
        }

        @Override
        public Object getEvent() {
            return event;
        }
    }

    /**
     * 同步执行
     */
    private static class SeqExecutorService implements Executor {
        private final static SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    /**
     * 异步执行
     */
    private static class PreExecutorService implements Executor {

        private final static PreExecutorService INSTANCE = new PreExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }
}
