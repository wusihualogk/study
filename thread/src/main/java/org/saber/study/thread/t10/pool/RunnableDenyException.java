package org.saber.study.thread.t10.pool;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/4 15:21
 **/
public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String message) {
        super(message);
    }

}
