package io.userservice.common.event.kafka.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
public class KafkaReceiverRunner implements CommandLineRunner {

    private final List<GenericKafkaReceiver> receivers;

    public KafkaReceiverRunner(List<GenericKafkaReceiver> kafkaReceivers) {
        receivers = new ArrayList<>();
        receivers.addAll(kafkaReceivers);
    }

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(receivers.size());
        for (GenericKafkaReceiver receiver : receivers) {
            executorService.submit(() -> {
                try {
                    receiver.execute();
                } catch (Exception e) {
                    System.err.println("Error in receiver: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
