package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:58
 **/
public class UserChatEventChannel extends AsyncChannel {
    @Override
    protected void handle(Event message) {
        UserChatEvent event = (UserChatEvent) message;
        System.out.println("用户" + event.getUser().getName() + "：" + event.getMessage());
    }
}
