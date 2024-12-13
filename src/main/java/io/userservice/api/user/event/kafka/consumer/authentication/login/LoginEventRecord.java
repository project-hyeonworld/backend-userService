package io.userservice.api.user.event.kafka.consumer.authentication.login;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 13.
 */
public record LoginEventRecord(
        long partyId,
        String userName
) {
}



