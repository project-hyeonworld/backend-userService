package io.userservice.common.event;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public interface EventPublisher<E extends CustomEvent> {
    void execute(E event);
}