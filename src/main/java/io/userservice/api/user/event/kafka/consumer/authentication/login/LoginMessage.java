package io.userservice.api.user.event.kafka.consumer.authentication.login;

import io.userservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 12.
 */
public record LoginMessage(
        long userId
)
        implements LoginEvent {

    @Override
    public Class<? extends CustomEvent> getEventClass() {
        return LoginEvent.class;
    }

    public static LoginMessage from(long userId) {
        return new LoginMessage(userId);
    }
}
