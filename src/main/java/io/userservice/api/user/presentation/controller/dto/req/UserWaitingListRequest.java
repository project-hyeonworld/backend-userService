package io.userservice.api.user.presentation.controller.dto.req;

import io.userservice.api.user.business.domain.in.RetrieveUserWaitingListCommand;

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
