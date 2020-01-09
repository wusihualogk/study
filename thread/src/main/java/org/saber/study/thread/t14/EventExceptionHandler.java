package org.saber.study.thread.t14;

/**
 * description:
 * 事件异常处理器
 *
 * @author: saber
 * @date: 2020/1/8 16:48
 **/
public interface EventExceptionHandler {

    void handler(Throwable cause, EventContext eventContext);

}
