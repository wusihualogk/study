package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:51
 **/
public class UserOnlineEvent extends Event {

    private final User user;

    public UserOnlineEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
