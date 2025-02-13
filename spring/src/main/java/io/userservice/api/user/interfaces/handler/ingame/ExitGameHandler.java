package io.userservice.api.user.interfaces.handler.ingame;

import io.userservice.api.user.business.UserService;
import io.userservice.api.user.event.kafka.UserEvent;
import io.userservice.api.user.event.kafka.consumer.ingame.exitame.ExitGameEvent;
import io.userservice.api.user.event.kafka.consumer.ingame.exitame.ExitGameMessage;
import io.userservice.common.event.GenericEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 24.
 */
@Component
public class ExitGameHandler extends GenericEventHandler<ExitGameEvent, UserEvent> {

    private final UserService userService;

    protected ExitGameHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Class<ExitGameEvent> getEventType() {
        return ExitGameEvent.class;
    }

    @Override
    public void handle(UserEvent event) {
        ExitGameMessage message = (ExitGameMessage) event;
        userService.confirmExitGame(message.userId());
    }
}
