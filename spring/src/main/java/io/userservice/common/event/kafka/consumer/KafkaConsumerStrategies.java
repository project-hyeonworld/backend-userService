package io.userservice.common.event.kafka.consumer;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
public class KafkaConsumerStrategies {
    private final List<GenericKafkaConsumer> consumers;

    @Autowired
    public KafkaConsumerStrategies(List<GenericKafkaConsumer> kafkaConsumers) {
        consumers = new ArrayList<>(kafkaConsumers);
    }

    public static KafkaConsumerStrategies from(List<GenericKafkaConsumer> collect) {
        return new KafkaConsumerStrategies(collect);
    }

    public void add(GenericKafkaConsumer strategy) {
        consumers.add(strategy);
    }

    public int size() {
        return consumers.size();
    }
}
