package io.userservice.api.user.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.userservice.api.user.infrastructure.entity.UserEntity;
import io.userservice.api.user.infrastructure.jpa.UserJpaRepository;
import io.userservice.api.user.infrastructure.jpa.UserJpaRepository.UserNameProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userJpaRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(long userId) {
        return userJpaRepository.findById(userId);
    }

    @Override
    public int deleteById(long userId) {
        return userJpaRepository.deleteById(userId);
    }

    @Override
    public List<UserEntity> findByLogin(boolean login) {
        return userJpaRepository.findByLogin(login);
    }

    @Override
    public Optional<UserEntity> findByName(String userName) {
        return userJpaRepository.findByName(userName);
    }

    @Override
    public List<UserNameProjection> findNamesByIds(Set<Long> userIds) {
        return userJpaRepository.findNamesByIdsIn(userIds.stream().toList());
    }

    @Override
    public void logOutAll() {
        userJpaRepository.logOutAll();
    }
}
