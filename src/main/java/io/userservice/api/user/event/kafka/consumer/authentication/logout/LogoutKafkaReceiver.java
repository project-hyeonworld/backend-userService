package io.userservice.api.user.event.kafka.consumer.authentication.logout;

import io.userservice.api.user.event.kafka.UserEventPublisher;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import io.userservice.common.event.kafka.consumer.GenericKafkaReceiver;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 14.
 */
@Component
public class LogoutKafkaReceiver extends GenericKafkaReceiver<LogoutEvent> {

    private final UserEventPublisher publisher;

    protected LogoutKafkaReceiver(
            GenericKafkaConsumerManager<LogoutEvent> kafkaConsumerManager, UserEventPublisher publisher) {
        super(kafkaConsumerManager);
        this.publisher = publisher;
    }

    @Override
    public void execute() {
        while (true) {
            handleEvents(manager.receive());
        }
    }

    private void handleEvents(List<LogoutEvent> events) {
        for (LogoutEvent event : events) {
            handleEvent(event);
        }
    }

    @Override
    protected void handleEvent(LogoutEvent event) {
        publisher.execute(event);
    }
}