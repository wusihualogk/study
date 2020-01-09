package org.saber.study.thread.t10;

import java.io.IOException;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/4 17:58
 **/
public class ClientServiceTest {

    public static void main(String[] args) throws IOException {
        ClientService clientService = new ClientService();
        clientService.startService();
    }
}
