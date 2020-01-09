package org.saber.study.thread.t14;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 21:01
 **/
public class FileChangeTest {

    public static void main(String[] args) throws IOException {
        new FileChangeTest().test();
    }

    public void test() throws IOException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        final EventBus eventBus = new AsyncEventBus(executor);
        eventBus.register(new FileChangeListener());

        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "E:\\");
        monitor.startMonitor();
    }
}
