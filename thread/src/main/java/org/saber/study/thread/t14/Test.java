package org.saber.study.thread.t14;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 17:49
 **/
public class Test {

    static class SimpleSubscriber1 {

        @Subscribe
        public void dsdf(Object event) {
            System.out.println("分发给很多subscriber,内容：" + event.toString());
        }

    }

    public static void main(String[] args) {

        EventBus eventBus = new EventBus();
        eventBus.register(new Test.SimpleSubscriber1());
        eventBus.register(new Test.SimpleSubscriber1());

        eventBus.post((Object) "dfsdf");

    }

}
