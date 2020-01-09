package org.saber.study.thread.t08;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/30 20:43
 **/
public class AutoSaveThread extends Thread {

    private final Document document;

    public AutoSaveThread(Document document) {
        super("AutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        while (true) {
            try {
                document.save();
                TimeUnit.SECONDS.sleep(3);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
