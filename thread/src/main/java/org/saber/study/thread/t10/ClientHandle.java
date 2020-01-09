package org.saber.study.thread.t10;

import java.io.*;
import java.net.Socket;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/4 17:42
 **/
public class ClientHandle implements Runnable {

    private final Socket socket;

    private final String clientIdentify;

    public ClientHandle(Socket socket) {
        this.socket = socket;
        clientIdentify = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
    }

    @Override
    public void run() {
        try {
            this.chat();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.release();
        }
    }

    private void writeClient(PrintStream printStream, String message) {
        printStream.println(message);
        printStream.flush();
    }

    private void chat() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream printStream = new PrintStream(socket.getOutputStream());

        String received;
        while ((received = bufferedReader.readLine()) != null) {
            System.out.println("client:" + Thread.currentThread().getName() + " " + clientIdentify + " " + received);
            if ("quit".equals(received)) {
                writeClient(printStream, received);
                socket.close();
                break;
            }
            writeClient(printStream, received);
        }
    }

    private void release() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
