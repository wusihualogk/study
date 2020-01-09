package org.saber.study.thread.t14;

/**
 * description:
 * 总线
 *
 * @author: saber
 * @date: 2020/1/7 17:20
 **/
public interface Bus {

    /**
     * 将某个对象注册到Bus上，从此之后该类就成为Subscriber（）了
     *
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * 将Bus上的某个对象取消注册
     *
     * @param subscriber
     */
    void unregister(Object subscriber);

    /**
     * 提交Event到默认的topic
     *
     * @param event
     */
    void post(Object event);

    /**
     * 提交Event到指定topic
     *
     * @param event
     * @param topic
     */
    void post(Object event, String topic);

    /**
     * 关闭该Bus
     */
    void close();

    /**
     * 返回Bus的名称
     *
     * @return
     */
    String getBusName();
}
