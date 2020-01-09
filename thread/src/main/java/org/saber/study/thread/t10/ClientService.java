package org.saber.study.thread.t10;

import org.saber.study.thread.t10.pool.BasicThreadPool;
import org.saber.study.thread.t10.pool.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/4 17:12
 **/
public class ClientService {

    private final int port;

    private ServerSocket serverSocket;

    private ThreadPool threadPool;

    public ClientService() {
        this(12306);
    }

    public ClientService(int port) {
        this.port = port;
    }

    public void startService() throws IOException {
        threadPool = new BasicThreadPool();
        serverSocket = new ServerSocket(port);
        //允许重用端口
        serverSocket.setReuseAddress(true);
        System.out.println("service: " + Thread.currentThread().getName() + "启动");

        listen();
    }


    public void listen() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.execute(new ClientHandle(socket));
        }
    }
}
