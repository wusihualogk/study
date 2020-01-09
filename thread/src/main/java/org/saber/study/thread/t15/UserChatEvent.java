package org.saber.study.thread.t15;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/8 22:53
 **/
public class UserChatEvent extends UserOnlineEvent {

    private final String message;

    public UserChatEvent(User user, String message) {
        super(user);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
