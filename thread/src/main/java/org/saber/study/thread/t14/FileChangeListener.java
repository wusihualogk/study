package org.saber.study.thread.t14;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 20:59
 **/
public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event) {
        System.out.println(event.getPath() + "--" + event.getKind());
    }

}
