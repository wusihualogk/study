package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 21:53
 **/
public class EventDispatcherExample {

    /**
     * 输入事件
     */
    static class InputEvent extends Event {

        private final int x;

        private final int y;

        public InputEvent(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    /**
     * 结果事件
     */
    static class ResultEvent extends Event {

        private final int result;

        public ResultEvent(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }

    /**
     * 处理ResultEvent的Handler(Channel)
     */
    static class ResultEventHandler implements Channel<ResultEvent> {
        @Override
        public void dispatch(ResultEvent message) {
            System.out.println("结果：" + message.getResult());
        }
    }

    /**
     * 处理InputEvent的Handler(Channel)
     */
    static class InputEventHandler implements Channel<InputEvent> {

        private final EventDispatcher dispatcher;

        public InputEventHandler(EventDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        @Override
        public void dispatch(InputEvent message) {
            System.out.println("x:" + message.getX() + ",y:" + message.getY());
            int result = message.getX() * message.getY();
            dispatcher.dispatch(new ResultEvent(result));
        }
    }

    public static void main(String[] args) {
        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.registerChannel(InputEvent.class, new InputEventHandler(dispatcher));
        dispatcher.registerChannel(ResultEvent.class, new ResultEventHandler());

        dispatcher.dispatch(new InputEvent(2, 5));
        dispatcher.dispatch(new InputEvent(3, 4));
        dispatcher.dispatch(new InputEvent(6, 5));
    }
}
