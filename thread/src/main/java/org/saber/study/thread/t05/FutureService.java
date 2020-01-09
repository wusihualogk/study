package org.saber.study.thread.t05;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/24 11:51
 **/
public interface FutureService<IN, OUT> {

    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<T, R>();
    }

    Future<?> submit(Runnable runnable);

    Future<OUT> submit(Task<IN, OUT> task, IN in);

    Future<OUT> submit(Task<IN, OUT> task, IN in, Callback callback);

}
