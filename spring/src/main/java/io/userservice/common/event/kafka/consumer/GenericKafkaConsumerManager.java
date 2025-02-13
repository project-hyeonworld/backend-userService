package io.userservice.common.event.kafka.consumer;

import io.userservice.common.event.CustomEvent;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 7.
 */
public abstract class GenericKafkaConsumerManager<E extends CustomEvent> implements
        KafkaConsumerManager<E> {

    public abstract List<E> receive();
}
