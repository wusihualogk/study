package org.saber.study.thread.t13;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 16:07
 **/
public class ActiveDaemonThread extends Thread {

    private final ActiveMessageQueue activeMessageQueue;

    public ActiveDaemonThread(ActiveMessageQueue activeMessageQueue) {
        super();
        this.activeMessageQueue = activeMessageQueue;
    }

    @Override
    public void run() {
        while (true) {
            activeMessageQueue.take().execute();
        }
    }
}
