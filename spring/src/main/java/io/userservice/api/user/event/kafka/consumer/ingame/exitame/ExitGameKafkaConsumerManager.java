package io.userservice.api.user.event.kafka.consumer.ingame.exitame;

import io.userservice.common.event.kafka.consumer.GenericKafkaConsumer;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 24.
 */
@Component
public class ExitGameKafkaConsumerManager extends GenericKafkaConsumerManager<ExitGameEvent> {

    private final GenericKafkaConsumer<ExitGameEvent, ?, ?> consumer;

    protected ExitGameKafkaConsumerManager(GenericKafkaConsumer<ExitGameEvent, ?, ?> consumer) {
        this.consumer = consumer;
    }

    @Override
    public List<ExitGameEvent> receive() {
        return consumer.receive();
    }
}
