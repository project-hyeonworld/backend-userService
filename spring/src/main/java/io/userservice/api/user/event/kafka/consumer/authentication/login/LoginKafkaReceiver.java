package io.userservice.api.user.event.kafka.consumer.authentication.login;

import io.userservice.api.user.event.kafka.UserEventPublisher;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import io.userservice.common.event.kafka.consumer.GenericKafkaReceiver;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 12.
 */
@Component
public class LoginKafkaReceiver extends GenericKafkaReceiver<LoginEvent> {

    private final GenericKafkaConsumerManager<LoginEvent> manager;
    private final UserEventPublisher publisher;

    protected LoginKafkaReceiver(
            GenericKafkaConsumerManager<LoginEvent> kafkaConsumerManager, UserEventPublisher userEventPublisher) {
        manager = kafkaConsumerManager;
        publisher = userEventPublisher;
    }

    @Override
    public void execute() {
        while (true) {
            handleEvents(manager.receive());
        }
    }

    private void handleEvents(List<LoginEvent> events) {
        for (LoginEvent event : events) {
            handleEvent(event);
        }
    }

    @Override
    protected void handleEvent(LoginEvent event) {
        publisher.execute(event);
    }
}
