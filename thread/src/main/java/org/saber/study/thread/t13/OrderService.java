package org.saber.study.thread.t13;

import org.saber.study.thread.t05.Future;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 11:07
 **/
public interface OrderService {

    /**
     * 获取订单详情
     *
     * @param orderId
     * @return
     */
    @ActiveMethod
    Future<String> findOrderDetails(long orderId);

    /**
     * 提交订单
     *
     * @param account
     * @param orderId
     */
    @ActiveMethod
    void order(String account, long orderId);
}
