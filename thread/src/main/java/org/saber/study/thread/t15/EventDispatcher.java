package org.saber.study.thread.t15;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 21:46
 **/
public class EventDispatcher implements DynamicRouter<Message> {

    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher() {
        routerTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException(message + "该消息未配置渠道！");
        }
    }
}
