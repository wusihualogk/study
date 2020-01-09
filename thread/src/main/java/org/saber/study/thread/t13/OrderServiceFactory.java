package org.saber.study.thread.t13;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 14:58
 **/
public class OrderServiceFactory {

    private final static MethodMessageQueue activeMessageQueue = new MethodMessageQueue();

    private OrderServiceFactory() {
    }

    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, activeMessageQueue);
    }

    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = toActiveObject(new OrderServiceImpl());

        orderService.order("sfds", 15465L);
        System.out.println("结束");
        Thread.currentThread().join();
    }
}
