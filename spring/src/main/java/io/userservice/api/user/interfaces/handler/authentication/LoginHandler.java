package io.userservice.api.user.interfaces.handler.authentication;

import io.userservice.api.user.business.UserService;
import io.userservice.api.user.event.kafka.UserEvent;
import io.userservice.api.user.event.kafka.consumer.authentication.login.LoginEvent;
import io.userservice.api.user.event.kafka.consumer.authentication.login.LoginMessage;
import io.userservice.common.event.GenericEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 13.
 */
@Component
public class LoginHandler extends GenericEventHandler<LoginEvent, UserEvent> {

    private final UserService userService;

    public LoginHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Class<LoginEvent> getEventType() {
        return LoginEvent.class;
    }

    @Override
    public void handle(UserEvent event) {
        LoginMessage message = (LoginMessage) event;
        userService.confirmLogin(message.userId());
    }
}
