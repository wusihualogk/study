package org.saber.study.thread.t13;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 11:44
 **/
public class MethodDaemonThread extends Thread {

    private final MethodMessageQueue queue;

    public MethodDaemonThread(MethodMessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            MethodMessage methodMessage = queue.take();
            methodMessage.execute();
        }
    }
}
