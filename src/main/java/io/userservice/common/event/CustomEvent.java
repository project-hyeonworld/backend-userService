package io.userservice.common.event;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public interface CustomEvent {
    Class<? extends CustomEvent> getEventClass();
}
