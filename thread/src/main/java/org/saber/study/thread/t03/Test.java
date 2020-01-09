package org.saber.study.thread.t03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 16:43
 **/
public class Test {

    private final static String text = "dsf";

    public static void main(String[] args) {
        System.out.println("start--" + System.currentTimeMillis());
        final ShareData shareData = new ShareData(50);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int k = 0; k < text.length(); k++) {
                    char c = text.charAt(k);
                    try {
                        shareData.write(c);
                        System.out.println(Thread.currentThread() + " write " + c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread() + " read " + new String(shareData.read()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread[] threadList = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threadList);
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end--" + System.currentTimeMillis());
    }
}
