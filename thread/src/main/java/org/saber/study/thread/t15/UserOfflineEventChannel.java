package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:57
 **/
public class UserOfflineEventChannel extends AsyncChannel {
    @Override
    protected void handle(Event message) {
        UserOfflineEvent event = (UserOfflineEvent) message;
        System.out.println("用户" + event.getUser().getName() + "下线");
    }
}
