package io.userservice.api.user.presentation.controller.dto.res;

import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record UserWaitingListResponse(List<String> names) {

    public static UserWaitingListResponse from(List<String> waitingNameList) {
        return new UserWaitingListResponse(waitingNameList);
    }
}
