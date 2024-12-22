package io.userservice.api.user.event.kafka.consumer.ingame.enterGame;

public record EnterGameEventRecord(
        long partyId,
        String userName
) {

}
