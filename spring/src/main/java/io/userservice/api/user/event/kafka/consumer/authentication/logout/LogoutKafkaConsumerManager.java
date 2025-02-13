package io.userservice.api.user.event.kafka.consumer.authentication.logout;

import io.userservice.common.event.kafka.consumer.GenericKafkaConsumer;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 14.
 */
@Component
public class LogoutKafkaConsumerManager extends GenericKafkaConsumerManager<LogoutEvent> {

    private final GenericKafkaConsumer<LogoutEvent, ?, ?> consumer;

    public LogoutKafkaConsumerManager(GenericKafkaConsumer<LogoutEvent, ?, ?> consumer) {
        this.consumer = consumer;
    }

    @Override
    public List<LogoutEvent> receive() {
        return consumer.receive();
    }
}
