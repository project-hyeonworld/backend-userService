package io.userservice.api.user.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record CreateUserCommand(
        String name,
        int relationType,
        byte relation
) {

}
