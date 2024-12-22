package io.userservice.api.user.event.kafka.consumer.ingame.enterGame;

import io.userservice.api.user.event.kafka.UserEvent;
import io.userservice.common.event.kafka.consumer.message.Message;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 22.
 */
public interface EnterGameEvent<T extends Message> extends UserEvent, Message {

}
