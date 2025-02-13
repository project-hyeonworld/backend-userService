package io.userservice.api.user.event.kafka.consumer.authentication.logout;

import io.userservice.api.user.event.kafka.UserEvent;
import io.userservice.common.event.kafka.consumer.message.Message;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 14.
 */
public interface LogoutEvent extends UserEvent, Message {

}