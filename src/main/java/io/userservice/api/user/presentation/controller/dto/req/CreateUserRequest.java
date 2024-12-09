package io.userservice.api.user.presentation.controller.dto.req;

import io.userservice.api.user.business.domain.in.CreateUserCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 21.
 */
public record CreateUserRequest(
        String name,
        int relationType,
        byte relation
) implements UserRequest {

    public CreateUserCommand toCommand() {
        return new CreateUserCommand(name, relationType, relation);
    }
}
