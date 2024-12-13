package io.userservice.api.user.event.kafka.consumer.authentication.login;

import io.userservice.common.event.kafka.consumer.GenericKafkaConsumer;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 12.
 */
@Component
public class LoginKafkaConsumerManager extends GenericKafkaConsumerManager<LoginEvent> {

    private final GenericKafkaConsumer<LoginEvent, ?, ?> consumer;

    protected LoginKafkaConsumerManager(
            GenericKafkaConsumer<LoginEvent, ?, ?> loginKafkaConsumers) {
        consumer = loginKafkaConsumers;
    }

    @Override
    public List<LoginEvent> receive() {
        return consumer.receive();
    }
}
