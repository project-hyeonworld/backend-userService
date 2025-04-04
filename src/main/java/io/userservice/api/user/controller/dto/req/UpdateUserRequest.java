package io.userservice.api.user.controller.dto.req;

import java.util.Optional;
import io.userservice.api.user.domain.dto.in.UpdateUserCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 21.
 */
public record UpdateUserRequest(
        String name,
        Integer relationType,
        Byte relation
) implements UserRequest {

    public UpdateUserCommand toCommand(long userId) {
        return new UpdateUserCommand(userId, Optional.ofNullable(name),
                Optional.ofNullable(relationType), Optional.ofNullable(relation));
    }
}
