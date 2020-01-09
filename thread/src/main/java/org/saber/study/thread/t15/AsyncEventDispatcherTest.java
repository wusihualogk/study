package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:46
 **/
public class AsyncEventDispatcherTest {

    public static void main(String[] args) {

        AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();

        dispatcher.registerChannel(EventDispatcherExample.InputEvent.class, new AsyncInputEventHandler(dispatcher));
        dispatcher.registerChannel(EventDispatcherExample.ResultEvent.class, new AsyncResultEventHandler());

        dispatcher.dispatch(new EventDispatcherExample.InputEvent(3, 5));
        dispatcher.dispatch(new EventDispatcherExample.InputEvent(5, 5));
        dispatcher.dispatch(new EventDispatcherExample.InputEvent(3, 3));
        dispatcher.dispatch(new EventDispatcherExample.InputEvent(3, 6));
        dispatcher.dispatch(new EventDispatcherExample.InputEvent(1, 2));
    }
}
