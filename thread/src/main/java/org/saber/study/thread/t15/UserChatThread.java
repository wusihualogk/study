package org.saber.study.thread.t15;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 23:00
 **/
public class UserChatThread extends Thread {

    private final User user;

    private final AsyncEventDispatcher dispatcher;

    public UserChatThread(User user, AsyncEventDispatcher dispatcher) {
        super(user.getName());
        this.dispatcher = dispatcher;
        this.user = user;
    }

    @Override
    public void run() {
        try {
            dispatcher.dispatch(new UserOnlineEvent(user));
            for (int i = 0; i < 5; i++) {
                dispatcher.dispatch(new UserChatEvent(user, getName() + "--Hello--" + i));

                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            dispatcher.dispatch(new UserOfflineEvent(user));
        }
    }
}
