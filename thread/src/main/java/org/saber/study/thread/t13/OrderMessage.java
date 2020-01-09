package org.saber.study.thread.t13;

import java.util.Map;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 14:22
 **/
public class OrderMessage extends MethodMessage {

    public OrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        orderService.order((String) params.get("account"), (Long) params.get("orderId"));
    }
}
