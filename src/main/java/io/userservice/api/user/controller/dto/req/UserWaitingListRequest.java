package io.userservice.api.user.controller.dto.req;

import io.userservice.api.user.domain.dto.in.RetrieveUserWaitingListCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record UserWaitingListRequest(
        long partyId
) implements UserRequest {

    public RetrieveUserWaitingListCommand toCommand() {
        return new RetrieveUserWaitingListCommand(partyId);
    }
}
