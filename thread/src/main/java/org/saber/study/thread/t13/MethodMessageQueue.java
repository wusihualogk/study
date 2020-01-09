package org.saber.study.thread.t13;

import java.util.LinkedList;

/**
 * description:
 * 传送Proxy提交过来的MethodMessage
 *
 * @author: saber
 * @date: 2020/1/7 11:41
 **/
public class MethodMessageQueue {

    private final LinkedList<MethodMessage> messageList = new LinkedList<>();

    public MethodMessageQueue() {
        new MethodDaemonThread(this).start();
    }

    public void offer(MethodMessage methodMessage) {
        synchronized (this) {
            messageList.addLast(methodMessage);
            this.notify();
        }
    }

    public MethodMessage take() {
        synchronized (this) {
            while (messageList.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return messageList.removeFirst();
        }
    }


}
