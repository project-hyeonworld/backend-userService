package io.userservice.common.event;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 5.
 */
public abstract class GenericEventListener<E extends CustomEvent> implements EventListener<E>{

    protected final Map<Class<? extends E>, GenericEventHandler<? extends E, E>> handlers;

    protected GenericEventListener(List<GenericEventHandler<? extends E, E>> eventHandlers) {
        handlers = eventHandlers.stream()
                .collect(Collectors.toMap(GenericEventHandler::getEventType, handler -> handler));
    }

    public abstract void handleEvent(E event);
}
