package org.saber.study.thread.t13;

import org.saber.study.thread.t05.Future;
import org.saber.study.thread.t05.FutureTask;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 11:51
 **/
public class ActiveFuture<T> extends FutureTask<T> {

    @Override
    public void finish(T result) {
        super.finish(result);
    }

}
