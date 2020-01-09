package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:45
 **/
public class AsyncResultEventHandler extends AsyncChannel {
    @Override
    protected void handle(Event message) {
        EventDispatcherExample.ResultEvent resultEvent = (EventDispatcherExample.ResultEvent) message;
        System.out.println("结果：" + resultEvent.getResult());
    }
}
