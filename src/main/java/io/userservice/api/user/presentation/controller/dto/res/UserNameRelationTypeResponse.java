package io.userservice.api.user.presentation.controller.dto.res;

import io.userservice.api.user.business.domain.out.UserInfo;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 20.
 */
public record UserNameRelationTypeResponse(
        int relationType,
        String name
) {

    public static UserNameRelationTypeResponse from(UserInfo userInfo) {
        return new UserNameRelationTypeResponse(userInfo.getRelationType().ordinal(), userInfo.getName());
    }
}
