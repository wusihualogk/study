package org.saber.study.thread.t15.p;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 21:30
 **/
public class Event {

    private final String type;

    private final String data;

    public Event(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
