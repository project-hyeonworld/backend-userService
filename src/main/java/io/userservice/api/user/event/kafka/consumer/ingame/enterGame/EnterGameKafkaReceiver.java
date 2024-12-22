package io.userservice.api.user.event.kafka.consumer.ingame.enterGame;

import io.userservice.api.user.event.kafka.UserEventPublisher;
import io.userservice.api.user.event.kafka.consumer.authentication.login.LoginEvent;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumer;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import io.userservice.common.event.kafka.consumer.GenericKafkaReceiver;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 22.
 */
@Component
public class EnterGameKafkaReceiver extends GenericKafkaReceiver<EnterGameEvent> {

    private final GenericKafkaConsumerManager<EnterGameEvent> manager;
    private final UserEventPublisher publisher;

    protected EnterGameKafkaReceiver(
            GenericKafkaConsumerManager<EnterGameEvent> kafkaConsumerManager, UserEventPublisher userEventPublisher) {
        manager = kafkaConsumerManager;
        publisher = userEventPublisher;
    }

    @Override
    protected void execute() {
        while (true) {
            handleEvents(manager.receive());
        }
    }

    private void handleEvents(List<EnterGameEvent> events) {
        for (EnterGameEvent event : events) {
            handleEvent(event);
        }
    }

    @Override
    protected void handleEvent(EnterGameEvent event) {
        publisher.execute(event);
    }
}
