package org.saber.study.thread.t14;

import java.io.IOException;
import java.nio.file.*;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 20:27
 **/
public class DirectoryTargetMonitor {

    private WatchService watchService;

    private final EventBus eventBus;

    private final Path path;

    private volatile boolean stop = false;

    public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath) {
        this(eventBus, targetPath, "");
    }

    public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath, final String... morePaths) {
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath, morePaths);
    }

    public void startMonitor() throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
        this.path.register(watchService,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);
        System.out.println("目录：" + path + "已被监控");

        while (!stop) {
            WatchKey watchKey = null;

            try {
                //发生事件时返回watchKey
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(event -> {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    //触发了事件，发布事件让接收者接收
                    eventBus.post(new FileChangeEvent(path, kind));
                    System.out.println(kind.name() + "---" + kind.type());
                });
            } catch (InterruptedException e) {
                this.stop = true;
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }

    public void stopMonitor() throws IOException {
        System.out.println("目录：" + path + "开始停止监控");
        Thread.currentThread().interrupt();
        this.stop = true;
        this.watchService.close();
        System.out.println("目录：" + path + "已停止监控");
    }
}
