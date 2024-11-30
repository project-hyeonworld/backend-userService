package io.userservice.api.user.controller.dto.res;

import io.userservice.api.user.domain.dto.out.UserInfo;
import java.util.List;
import java.util.stream.Collectors;
import io.userservice.api.user.domain.dto.out.UserInfos;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record UserResponse(
        Long id,
        String name,
        Integer relationType,
        String nickname,
        Byte relation
) {

    public static List<UserResponse> from(UserInfos userInfos) {
        return userInfos.getItems().stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    public static UserResponse from(UserInfo userInfo) {
        return new UserResponse(
                userInfo.getId(),
                userInfo.getName(),
                userInfo.getRelationType().ordinal(),
                userInfo.getNickname(),
                userInfo.getRelation());
    }
}
