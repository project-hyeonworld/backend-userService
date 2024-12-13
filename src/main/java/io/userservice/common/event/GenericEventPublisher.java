package io.userservice.common.event;

import org.springframework.context.ApplicationEventPublisher;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 5.
 */
public abstract class GenericEventPublisher<E extends CustomEvent> implements EventPublisher<E> {

    protected final ApplicationEventPublisher publisher;

    protected GenericEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void execute(E event) {
        publisher.publishEvent(event);
    }
}
