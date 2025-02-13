package io.userservice.common.event.kafka.producer;

import io.userservice.common.event.CustomEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public interface KafkaProducerStrategy<E extends CustomEvent, K, V> {

    String getTopic();
    KafkaProducer<K, V> getProducer();
    Class<E> getEventClass();

    default String getEventName() {
        return getEventClass().getName();
    }

    default void send(E event) {
        getProducer().send(produce(event));
    }

    default ProducerRecord produce(E event) {
        return new ProducerRecord<>(getTopic(), event);
    }

    void close();
}
