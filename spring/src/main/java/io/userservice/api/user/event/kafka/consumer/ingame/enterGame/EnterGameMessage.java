package io.userservice.api.user.event.kafka.consumer.ingame.enterGame;

import io.userservice.common.event.CustomEvent;
import io.userservice.common.event.kafka.consumer.message.Message;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 22.
 */
public record EnterGameMessage (
        long userId
) implements EnterGameEvent, Message {

    @Override
    public Class<? extends CustomEvent> getEventClass() {
        return EnterGameEvent.class;
    }

    public static EnterGameMessage from(Long userId) {
        return new EnterGameMessage(userId);
    }
}


