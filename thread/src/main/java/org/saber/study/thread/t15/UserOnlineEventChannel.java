package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:55
 **/
public class UserOnlineEventChannel extends AsyncChannel {
    @Override
    protected void handle(Event message) {
        UserOnlineEvent event = (UserOnlineEvent) message;
        System.out.println("用户" + event.getUser().getName() + "上线");
    }
}
