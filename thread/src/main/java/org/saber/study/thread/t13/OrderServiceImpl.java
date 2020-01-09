package org.saber.study.thread.t13;

import org.saber.study.thread.t05.Future;
import org.saber.study.thread.t05.FutureService;

import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 11:09
 **/
public class OrderServiceImpl implements OrderService {

    @ActiveMethod
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(in -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("成功返回订单列表");
            return "成功返回订单列表-" + System.nanoTime();
        }, orderId);
    }

    @ActiveMethod
    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("成功添加订单" + orderId + "账户" + account);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
