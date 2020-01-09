package org.saber.study.thread.t08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/30 20:43
 **/
public class Document {

    /**
     * 如果文档发生变化，changed会被设置为true
     */
    private boolean changed = false;

    private List<String> content = new ArrayList<>();

    private final FileWriter writer;

    private static AutoSaveThread autoSaveThread;

    private Document(String documentPath, String documentName) throws IOException {
        this.writer = new FileWriter(new File(documentPath, documentName));
    }

    public static Document create(String documentPath, String documentName) throws IOException {
        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    public void edit(String content) {
        synchronized (this) {
            this.content.add(content);
            this.changed = true;
        }
    }

    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    public void save() throws IOException {
        synchronized (this) {
            if (!changed) {
                return;
            }

            System.out.println(Thread.currentThread() + " 保存");
            for (String cacheLine : content) {
                this.writer.append(cacheLine);
                this.writer.append("\r\n");
            }
            this.writer.flush();
            this.changed = false;
            this.content.clear();
        }
    }

}
