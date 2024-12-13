package io.userservice.common.event.kafka.consumer;

import io.userservice.common.event.CustomEvent;
import io.userservice.common.event.EventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaReceiver<E extends CustomEvent> extends EventListener<E> {
}
