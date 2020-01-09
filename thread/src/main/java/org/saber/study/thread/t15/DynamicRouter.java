package org.saber.study.thread.t15;

/**
 * description:
 * 动态路由
 *
 * @author: saber
 * @date: 2020/1/8 21:40
 **/
public interface DynamicRouter<E extends Message> {

    /**
     * 针对每种Message注册channel
     *
     * @param messageType
     * @param channel
     */
    void registerChannel(Class<? extends E> messageType,
                         Channel<? extends E> channel);

    /**
     * 为指定的Channel分配Message
     *
     * @param message
     */
    void dispatch(E message);
}
