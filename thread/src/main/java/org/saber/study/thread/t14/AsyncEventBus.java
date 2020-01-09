package org.saber.study.thread.t14;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 17:32
 **/
public class AsyncEventBus extends EventBus {

    AsyncEventBus(String busName, EventExceptionHandler eventExceptionHandler, ThreadPoolExecutor executor) {
        super(busName, eventExceptionHandler, executor);
    }

    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        this(busName, null, executor);
    }

    public AsyncEventBus(ThreadPoolExecutor executor) {
        this("default-sync", executor);
    }

    public AsyncEventBus(EventExceptionHandler eventExceptionHandler, ThreadPoolExecutor executor) {
        this("default-sync", eventExceptionHandler, executor);
    }
}
