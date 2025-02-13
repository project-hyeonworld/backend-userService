package io.userservice.common.event;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 5.
 */
public abstract class GenericEventHandler<EE extends E, E extends CustomEvent> implements EventHandler<E> {

    public abstract Class<EE> getEventType();
    public abstract void handle(E event);
}
