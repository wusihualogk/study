package org.saber.study.thread.t01;

import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/23 11:55
 **/
public class Test {

    public static void main(String[] args) {

        Observable observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("完成");
            return "返回结果";
        });
        observableThread.start();

        Task task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("完成");
            return "返回结果";
        };
    }

}
