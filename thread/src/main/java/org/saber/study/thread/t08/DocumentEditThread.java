package org.saber.study.thread.t08;

import java.io.IOException;
import java.util.Scanner;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/30 20:44
 **/
public class DocumentEditThread extends Thread {

    private final String documentPath;

    private final String documentName;

    private final Scanner scanner = new Scanner(System.in);

    public DocumentEditThread(String documentPath, String documentName) {
        super("DocumentEditThread");
        this.documentPath = documentPath;
        this.documentName = documentName;
    }

    @Override
    public void run() {
        int times = 0;
        try {
            Document document = Document.create(documentPath, documentName);
            while (true) {
                String text = scanner.next();
                if ("quit".equals(text)) {
                    document.close();
                    break;
                }

                //每5次保存一次
                document.edit(text);
                if (times >= 5) {
                    times = 0;
                    document.save();
                }
                times++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DocumentEditThread thread = new DocumentEditThread("E:\\", "common-all.log");
        thread.start();
    }
}
