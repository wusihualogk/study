package org.saber.study.thread.t15.p;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 21:35
 **/
public class FooEventDrivenExample {

    public static void handleEventA(Event e) {
        System.out.println(e.getData().toLowerCase());
    }

    public static void handleEventB(Event e) {
        System.out.println(e.getData().toUpperCase());
    }

}
