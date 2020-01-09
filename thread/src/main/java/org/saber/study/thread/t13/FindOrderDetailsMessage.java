package org.saber.study.thread.t13;

import org.saber.study.thread.t05.Future;

import java.util.Map;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 11:55
 **/
public class FindOrderDetailsMessage extends MethodMessage {

    public FindOrderDetailsMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        Future<String> future = orderService.findOrderDetails((Long) params.get("orderId"));
        ActiveFuture<String> activeFuture = (ActiveFuture<String>) params.get("activeFuture");
        try {
            String result = future.get();
            activeFuture.finish(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
