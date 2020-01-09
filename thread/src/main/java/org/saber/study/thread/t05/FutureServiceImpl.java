package org.saber.study.thread.t05;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/24 11:56
 **/
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    /**
     * 线程名称前缀
     */
    private final static String FUTURE_THERAD_PREFIX = "FUTURE-";

    private final static AtomicInteger nextCount = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THERAD_PREFIX + nextCount.getAndIncrement();
    }


    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            futureTask.finish(null);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN in) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT out = task.get(in);
            futureTask.finish(out);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN in, Callback callback) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT out = task.get(in);
            futureTask.finish(out);
            if (callback != null) {
                callback.callback(out);
            }
        }, getNextName()).start();
        return futureTask;
    }
}
