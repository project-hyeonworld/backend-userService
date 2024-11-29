package io.userservice.api.user.controller.dto.res;

import java.util.List;
import java.util.stream.Collectors;
import io.userservice.api.user.domain.dto.out.User;
import io.userservice.api.user.domain.dto.out.Users;

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

    public static List<UserResponse> from(Users users) {
        return users.getItems().stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getRelationType().ordinal(),
                user.getNickname(),
                user.getRelation());
    }
}
