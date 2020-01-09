package org.saber.study.thread.t13;

import org.saber.study.thread.t05.Future;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * 将orderService的每一个方法都封装成MethodMessage并放入ActiveMessage队列
 *
 * @author: saber
 * @date: 2020/1/7 11:37
 **/
public class OrderServiceProxy implements OrderService {

    private final OrderService orderService;

    private final MethodMessageQueue activeMessageQueue;

    public OrderServiceProxy(OrderService orderService, MethodMessageQueue activeMessageQueue) {
        this.orderService = orderService;
        this.activeMessageQueue = activeMessageQueue;
    }

    @Override
    public Future<String> findOrderDetails(long orderId) {
        final ActiveFuture<String> activeFuture = new ActiveFuture();
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("activeFuture", activeFuture);
        MethodMessage message = new FindOrderDetailsMessage(params, orderService);

        activeMessageQueue.offer(message);
        return activeFuture;
    }

    @Override
    public void order(String account, long orderId) {
        final ActiveFuture<String> activeFuture = new ActiveFuture();
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("account", account);

        MethodMessage message = new OrderMessage(params, orderService);
        activeMessageQueue.offer(message);
    }
}
