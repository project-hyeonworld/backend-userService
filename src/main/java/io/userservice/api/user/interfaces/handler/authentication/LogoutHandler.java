package io.userservice.api.user.interfaces.handler.authentication;

import io.userservice.api.user.business.UserService;
import io.userservice.api.user.event.kafka.UserEvent;
import io.userservice.api.user.event.kafka.consumer.authentication.logout.LogoutEvent;
import io.userservice.api.user.event.kafka.consumer.authentication.logout.LogoutMessage;
import io.userservice.common.event.GenericEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 14.
 */
@Component
public class LogoutHandler extends GenericEventHandler<LogoutEvent, UserEvent> {

    private final UserService userService;

    public LogoutHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Class<LogoutEvent> getEventType() {
        return LogoutEvent.class;
    }

    @Override
    public void handle(UserEvent event) {
        LogoutMessage message = (LogoutMessage) event;
        userService.confirmLogout(message.userId());
    }
}
