package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 23:09
 **/
public class UserChatApplication {

    public static void main(String[] args) {
        final AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();
        dispatcher.registerChannel(UserOnlineEvent.class, new UserOnlineEventChannel());
        dispatcher.registerChannel(UserChatEvent.class, new UserChatEventChannel());
        dispatcher.registerChannel(UserOfflineEvent.class, new UserOfflineEventChannel());

        new UserChatThread(new User("爱丽丝"), dispatcher).start();
        new UserChatThread(new User("艾瑞利亚"), dispatcher).start();
        new UserChatThread(new User("剑姬"), dispatcher).start();
    }

}
