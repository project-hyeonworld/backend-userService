package io.userservice.common.event.kafka.consumer;

import io.userservice.common.event.CustomEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 3.
 */
public abstract class GenericKafkaConsumer<E extends CustomEvent, K, V> implements KafkaConsumerStrategy {

    protected Duration timeout;
    protected KafkaConsumer<K, V> kafkaConsumer;

    public abstract Class<E> getEventClass();

    public List<E> receive() {
        List<E> events = new ArrayList<>();
        ConsumerRecords<K, V> records = consume();
        for (ConsumerRecord<K, V> record : records) {
            events.add(convertToEvent(record));
        }
        return events;
    }

    protected ConsumerRecords<K, V> consume() {
        return kafkaConsumer.poll(timeout);
    }

    protected abstract E convertToEvent(ConsumerRecord<K, V> record);
}
