package io.userservice.api.user.infrastructure;

import io.userservice.api.user.infrastructure.entity.UserEntity;
import io.userservice.api.user.infrastructure.jpa.UserJpaRepository.UserNameProjection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface UserRepository {

    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findById(long userId);

    List<UserEntity> findAll();

    int deleteById(long userId);

    List<UserEntity> findByLogin(boolean login);

    Optional<UserEntity> findByName(String userName);

    List<UserNameProjection> findNamesByIds(Set<Long> userIds);

    void logOutAll();
}
