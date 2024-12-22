package io.userservice.common.event.kafka.producer;

import io.userservice.common.event.CustomEvent;
import io.userservice.common.event.EventPublisher;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaSender<E extends CustomEvent> extends EventPublisher<E> {
}
