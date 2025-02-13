package io.userservice.api.user.event.kafka.consumer.authentication.login;

import io.userservice.common.event.kafka.consumer.GenericKafkaConsumer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 12.
 */
@Component
public class LoginKafkaConsumer extends GenericKafkaConsumer<LoginEvent, Long, String> {

    public LoginKafkaConsumer(@Value("${spring.kafka.broker.url}")String brokerUrl, @Value("${spring.kafka.topic.session.authentication.log-in}")String topic, @Value("${spring.application.name}") String groupId) {
        timeout = Duration.ofMillis(1000);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singletonList(topic));
    }

    @Override
    public Class<LoginEvent> getEventClass() {
        return LoginEvent.class;
    }

    @Override
    public List<LoginEvent> receive() {
        List<LoginEvent> events = new ArrayList<>();
        ConsumerRecords<Long, String> records = consume();
        for (ConsumerRecord<Long, String> record : records) {
            events.add(convertToEvent(record));
        }
        return events;
    }

    @Override
    protected LoginEvent convertToEvent(ConsumerRecord<Long, String> record) {
        return LoginMessage.from(record.key());
    }
}
