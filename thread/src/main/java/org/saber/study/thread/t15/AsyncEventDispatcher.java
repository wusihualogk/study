package org.saber.study.thread.t15;

import org.saber.study.thread.t15.Channel;
import org.saber.study.thread.t15.DynamicRouter;
import org.saber.study.thread.t15.Event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:25
 **/
public class AsyncEventDispatcher implements DynamicRouter<Event> {

    private final Map<Class<? extends Event>, AsyncChannel> routerTable;

    public AsyncEventDispatcher() {
        routerTable = new ConcurrentHashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Event> messageType, Channel<? extends Event> channel) {
        if (!(channel instanceof AsyncChannel)) {
            throw new IllegalArgumentException("渠道类型错误");
        }
        routerTable.put(messageType, (AsyncChannel) channel);
    }

    @Override
    public void dispatch(Event message) {
        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException(message.getType() + "未找到对应渠道");
        }
    }

    public void showdown() {
        routerTable.values().forEach(AsyncChannel::stop);
    }
}
