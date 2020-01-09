package org.saber.study.thread.t14;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * description:
 * 注册表
 *
 * @author: saber
 * @date: 2020/1/8 9:57
 **/
public class Registry {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscriberContainer = new ConcurrentHashMap<>();

    /**
     * 绑定注册者
     *
     * @param subscriber
     */
    public void bind(Object subscriber) {
        List<Method> subscriberMethods = getSubscribeMethods(subscriber);
        subscriberMethods.forEach(m -> tierSubscriber(subscriber, m));
        System.out.println("绑定注册者：" + subscriber.toString());
    }

    /**
     * 解除注册者绑定，空间换时间只设置失效
     *
     * @param subscriber
     */
    public void unbind(Object subscriber) {
        subscriberContainer.forEach((key, value) -> {
            value.forEach(s -> {
                if (s.getSubscriberObject().equals(subscriber)) {
                    s.setDisable(true);
                }
            });
        });
    }

    /**
     * 层叠注册者
     *
     * @param subscriber
     * @param method
     */
    private void tierSubscriber(Object subscriber, Method method) {
        final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());

        subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }

    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(String topic) {
        return subscriberContainer.get(topic);
    }

    /**
     * 获取特定方法列表
     *
     * @param subscriber
     * @return
     */
    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methodList = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null) {
            Method[] declaredMethods = temp.getDeclaredMethods();
            //只取public、被@Subscribe标记、一个入参
            Arrays.stream(declaredMethods)
                    .filter(m -> m.isAnnotationPresent(Subscribe.class)
                            && m.getParameterCount() == 1
                            && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methodList::add);
            temp = temp.getSuperclass();
        }
        return methodList;
    }

}
