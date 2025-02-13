package io.userservice.api.user.event.kafka.consumer.ingame.exitame;

import io.userservice.common.event.CustomEvent;
import io.userservice.common.event.kafka.consumer.message.Message;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 24.
 */
public record ExitGameMessage (
        long userId
) implements ExitGameEvent, Message {

    @Override
    public Class<? extends CustomEvent> getEventClass() {
        return ExitGameEvent.class;
    }

    public static ExitGameEvent from(Long userId) {
        return new ExitGameMessage(userId);
    }
}
