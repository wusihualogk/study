package org.saber.study.thread.t15;

import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:38
 **/
public class AsyncInputEventHandler extends AsyncChannel {

    private final AsyncEventDispatcher dispatcher;

    public AsyncInputEventHandler(AsyncEventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    protected void handle(Event message) {
        EventDispatcherExample.InputEvent inputEvent = (EventDispatcherExample.InputEvent) message;
        System.out.println("x:" + inputEvent.getX() + ",y=" + inputEvent.getY());
        int result = inputEvent.getX() * inputEvent.getY();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dispatcher.dispatch(new EventDispatcherExample.ResultEvent(result));
    }
}
