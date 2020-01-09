package org.saber.study.thread.t15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:20
 **/
public abstract class AsyncChannel implements Channel<Event> {

    /**
     * 在AsyncChannel中使用ExecutorService多线程的方式提交给Message
     */
    private final ExecutorService executorService;

    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    public AsyncChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public final void dispatch(Event message) {
        executorService.submit(() -> {
            handle(message);
        });
    }

    protected abstract void handle(Event message);

    void stop() {
        if (null != executorService && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

}
