package io.userservice.api.user.event.kafka.consumer.authentication.logout;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 14.
 */
public record LogoutEventRecord(
        long partyId,
        String userName
) {
}