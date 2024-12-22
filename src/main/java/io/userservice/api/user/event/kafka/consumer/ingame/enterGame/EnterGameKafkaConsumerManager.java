package io.userservice.api.user.event.kafka.consumer.ingame.enterGame;

import io.userservice.common.event.kafka.consumer.GenericKafkaConsumer;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 22.
 */
@Component
public class EnterGameKafkaConsumerManager extends GenericKafkaConsumerManager<EnterGameEvent> {

    private final GenericKafkaConsumer<EnterGameEvent, ?, ?> consumer;

    protected EnterGameKafkaConsumerManager(GenericKafkaConsumer<EnterGameEvent, ?, ?> consumer) {
        this.consumer = consumer;
    }


    @Override
    public List<EnterGameEvent> receive() {
        return consumer.receive();
    }
}
