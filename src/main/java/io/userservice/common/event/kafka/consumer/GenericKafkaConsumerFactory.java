package io.userservice.common.event.kafka.consumer;

import io.userservice.common.event.CustomEvent;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public abstract class GenericKafkaConsumerFactory<E extends CustomEvent> implements
        KafkaConsumerStrategy {

    protected final Map<Class<? extends E>, GenericKafkaConsumer<? extends E, ?, ?>> consumers;

    protected GenericKafkaConsumerFactory(List<GenericKafkaConsumer<? extends E, ?, ?>> kafkaConsumers) {
        consumers = kafkaConsumers.stream()
                .collect(Collectors.toMap(GenericKafkaConsumer::getEventClass, strategy -> strategy));
    }

    public GenericKafkaConsumer getConsumer(Class<? extends E> eventClass) {
        return consumers.get(eventClass);
    }

    public abstract Class<? extends CustomEvent> getEventClass();
}


