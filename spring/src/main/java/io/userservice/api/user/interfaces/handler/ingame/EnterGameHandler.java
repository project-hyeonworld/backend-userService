package io.userservice.api.user.interfaces.handler.ingame;

import io.userservice.api.user.business.UserService;
import io.userservice.api.user.event.kafka.UserEvent;
import io.userservice.api.user.event.kafka.consumer.ingame.enterGame.EnterGameEvent;
import io.userservice.api.user.event.kafka.consumer.ingame.enterGame.EnterGameMessage;
import io.userservice.common.event.GenericEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 22.
 */
@Component
public class EnterGameHandler extends GenericEventHandler<EnterGameEvent, UserEvent> {

    private final UserService userService;

    protected EnterGameHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Class<EnterGameEvent> getEventType() {
        return EnterGameEvent.class;
    }

    @Override
    public void handle(UserEvent event) {
        EnterGameMessage message = (EnterGameMessage) event;
        userService.confirmEnterGame(message.userId());
    }
}
