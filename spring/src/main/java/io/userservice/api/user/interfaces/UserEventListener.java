package io.userservice.api.user.interfaces;

import io.userservice.api.user.event.kafka.UserEvent;
import io.userservice.common.event.GenericEventHandler;
import io.userservice.common.event.GenericEventListener;
import java.util.List;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 13.
 */
@Component
public class UserEventListener extends GenericEventListener<UserEvent> {

    protected UserEventListener(
            List<GenericEventHandler<? extends UserEvent, UserEvent>> genericEventHandlers) {
        super(genericEventHandlers);
    }

    @Override
    @EventListener
    public void handleEvent(UserEvent event) {
        handlers.get(event.getEventClass()).handle(event);
    }
}
