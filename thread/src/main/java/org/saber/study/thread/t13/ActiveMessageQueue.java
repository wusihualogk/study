package org.saber.study.thread.t13;

import java.util.LinkedList;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 16:05
 **/
public class ActiveMessageQueue {

    public final LinkedList<ActiveMessage> activeMessageQueue = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemonThread(this).start();
    }

    public void offer(ActiveMessage activeMessage) {
        synchronized (this) {
            activeMessageQueue.addLast(activeMessage);
            this.notify();
        }
    }

    public ActiveMessage take() {
        synchronized (this) {
            while (activeMessageQueue.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return activeMessageQueue.removeFirst();
        }
    }

}
