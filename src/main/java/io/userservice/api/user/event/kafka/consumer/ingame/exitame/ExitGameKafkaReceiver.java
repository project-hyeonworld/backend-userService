package io.userservice.api.user.event.kafka.consumer.ingame.exitame;

import io.userservice.api.user.event.kafka.UserEventPublisher;
import io.userservice.common.event.kafka.consumer.GenericKafkaConsumerManager;
import io.userservice.common.event.kafka.consumer.GenericKafkaReceiver;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 24.
 */
@Component
public class ExitGameKafkaReceiver extends GenericKafkaReceiver<ExitGameEvent> {

    private final GenericKafkaConsumerManager<ExitGameEvent> manager;
    private final UserEventPublisher publisher;

    protected ExitGameKafkaReceiver(GenericKafkaConsumerManager<ExitGameEvent> manager, UserEventPublisher publisher) {
        this.manager = manager;
        this.publisher = publisher;
    }

    @Override
    protected void execute() {
        while (true) {
            handleEvents(manager.receive());
        }
    }

    private void handleEvents(List<ExitGameEvent> events) {
        for (ExitGameEvent event : events) {
            handleEvent(event);
        }
    }

    @Override
    protected void handleEvent(ExitGameEvent event) {
        publisher.execute(event);
    }
}
