package io.userservice.common.event.kafka.consumer;

import io.userservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 8.
 */
public abstract class GenericKafkaReceiver<E extends CustomEvent> implements KafkaReceiver<E> {

    protected GenericKafkaConsumerManager<E> manager;

    protected GenericKafkaReceiver(GenericKafkaConsumerManager<E> kafkaConsumerManager) {
        manager = kafkaConsumerManager;
    }

    protected abstract void execute();
    protected abstract void handleEvent(E event);

}
