package io.userservice.common.event.kafka.consumer;

import io.userservice.common.event.CustomEvent;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 7.
 */
public abstract class GenericKafkaConsumers<E extends CustomEvent> implements
        KafkaConsumerStrategy {

    protected List<GenericKafkaConsumer<? extends E, ?, ?>> cosumers;
    protected final Executor executor;

    protected GenericKafkaConsumers(List<GenericKafkaConsumer<? extends E, ?, ?>> kafkaProducerStrategies) {
        cosumers = kafkaProducerStrategies;
        executor = Executors.newFixedThreadPool(cosumers.size());
    }

    public int size() {
        return cosumers.size();
    }

    public abstract List<E> receive();
}
