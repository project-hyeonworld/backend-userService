package io.userservice.api.user.presentation.controller.dto.res;

import io.userservice.api.user.business.domain.out.UserInfo;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 5.
 */
public record UserRelationTypeResponse(
        long id,
        int relationType
) {

    public static UserRelationTypeResponse from(UserInfo userInfo) {
        return new UserRelationTypeResponse(userInfo.getId(), (int)userInfo.getRelation());
    }
}
