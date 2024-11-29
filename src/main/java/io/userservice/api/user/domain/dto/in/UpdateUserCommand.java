package io.userservice.api.user.domain.dto.in;

import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record UpdateUserCommand(
        Long userId,
        Optional<String> name,
        Optional<Integer> relationType,
        Optional<Byte> relation
) {

}
