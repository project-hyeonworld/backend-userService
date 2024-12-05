package io.userservice.api.user.controller.dto.res;

import io.userservice.api.user.domain.dto.out.UserInfo;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 5.
 */
public record UserRelationTypeRepsonse(
        long id,
        int relationType
) {

    public static UserRelationTypeRepsonse from(UserInfo userInfo) {
        return new UserRelationTypeRepsonse(userInfo.getId(), (int)userInfo.getRelation());
    }
}
