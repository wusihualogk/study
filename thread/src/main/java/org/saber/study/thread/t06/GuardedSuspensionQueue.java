package org.saber.study.thread.t06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/27 11:14
 **/
public class GuardedSuspensionQueue {

    private Logger logger = LoggerFactory.getLogger(GuardedSuspensionQueue.class);

    private LinkedList<String> queue = new LinkedList<>();

    private final int MAX_SIZE = 50;

    public void offer() throws InterruptedException {
        synchronized (this) {
            while (queue.size() > MAX_SIZE) {
                this.wait();
            }
            String s = UUID.randomUUID().toString();
            queue.add(s);
//            TimeUnit.SECONDS.sleep(1);
            System.out.println("添加" + s + "成功！" + queue.size());
            this.notifyAll();
        }
    }

    public void task() throws InterruptedException {
        synchronized (this) {
            while (queue.size() <= 0) {
                this.wait();
            }
//            TimeUnit.SECONDS.sleep(1);
            this.notifyAll();
            String s = queue.removeFirst();
            System.out.println("移除" + s + "成功！" + queue.size());
        }
    }

    public static void main(String[] args) {
        GuardedSuspensionQueue queue = new GuardedSuspensionQueue();

        new Thread(() -> {
            while (true) {
                try {
                    queue.offer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    queue.task();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
