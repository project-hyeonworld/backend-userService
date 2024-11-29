package io.userservice.api.user.domain.dto.out;

import io.userservice.api.user.infrastructure.entity.UserEntity;
import io.userservice.api.user.infrastructure.jpa.UserJpaRepository.UserNameProjection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public class Users {

    private final List<User> users;

    public Users(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    public static Users from(List<UserEntity> userEntities) {
        return new Users(userEntities.stream()
                .map(User::from)
                .collect(Collectors.toList()));
    }

    public static Map<Long, String> getNames(List<UserNameProjection> namesByIds) {
        return namesByIds.stream()
                .collect(Collectors.toMap(
                        UserNameProjection::getId,
                        UserNameProjection::getName,
                        (existing, replacement) -> existing, LinkedHashMap::new
                ));
    }

    public List<User> getItems() {
        return users;
    }

    public int size() {
        return users.size();
    }
}