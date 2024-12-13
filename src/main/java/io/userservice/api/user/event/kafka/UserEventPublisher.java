package io.userservice.api.user.event.kafka;

import io.userservice.common.event.GenericEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 11.
 */
@Component
public class UserEventPublisher extends GenericEventPublisher<UserEvent> {

    protected UserEventPublisher(ApplicationEventPublisher publisher) {
        super(publisher);
    }
}
