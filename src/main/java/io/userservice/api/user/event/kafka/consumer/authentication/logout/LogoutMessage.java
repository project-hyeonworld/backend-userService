package io.userservice.api.user.event.kafka.consumer.authentication.logout;

import io.userservice.api.user.event.kafka.consumer.authentication.login.LoginEvent;
import io.userservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 14.
 */
public record LogoutMessage (
        long userId
)implements LogoutEvent {

    @Override
    public Class<? extends CustomEvent> getEventClass() {
        return LogoutEvent.class;
    }

    public static LogoutMessage from(long userId) {
        return new LogoutMessage(userId);
    }
}