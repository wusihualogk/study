package org.saber.study.thread.t14;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 20:51
 **/
public class FileChangeEvent {

    private final Path path;

    private final WatchEvent.Kind<?> kind;

    public FileChangeEvent(Path path, WatchEvent.Kind<?> kind) {
        this.path = path;
        this.kind = kind;
    }

    public Path getPath() {
        return path;
    }

    public WatchEvent.Kind<?> getKind() {
        return kind;
    }
}
