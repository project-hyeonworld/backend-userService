package io.userservice.api.user.domain.dto.out;

import io.userservice.api.user.infrastructure.entity.User;
import io.userservice.api.user.infrastructure.jpa.UserJpaRepository.UserNameProjection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public class UserInfos {

    private final List<UserInfo> userInfos;

    public UserInfos(List<UserInfo> userInfos) {
        this.userInfos = new ArrayList<>(userInfos);
    }

    public static UserInfos from(List<User> userEntities) {
        return new UserInfos(userEntities.stream()
                .map(UserInfo::from)
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

    public List<UserInfo> getItems() {
        return userInfos;
    }

    public int size() {
        return userInfos.size();
    }
}