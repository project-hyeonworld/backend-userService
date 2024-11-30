package io.userservice.api.user.domain;

import static io.userservice.api.user.domain.dto.out.User.*;
import static io.userservice.api.user.domain.dto.out.Users.*;

import io.userservice.common.exception.ExceptionResponseCode;
import io.userservice.common.exception.UserException;
import io.userservice.api.postPosition.PostpositionService;
import io.userservice.api.user.domain.dto.in.CreateUserCommand;
import io.userservice.api.user.domain.dto.in.RetrieveUserWaitingListCommand;
import io.userservice.api.user.domain.dto.in.UpdateUserCommand;
import io.userservice.api.user.domain.dto.out.NameGameStatuses;
import io.userservice.api.user.domain.dto.out.User;
import io.userservice.api.user.domain.dto.out.Users;
import io.userservice.api.user.infrastructure.entity.RelationType;
import io.userservice.api.user.infrastructure.UserRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostpositionService postpositionService;

    public User createUser(CreateUserCommand command) {

        return from(userRepository.save(toEntity(command, postpositionService.josa(command.name()))));
    }

    public Users getAllUsers() {
        return from(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    public User getUserById(long userId) {
        return from(userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ExceptionResponseCode.USER_NOT_FOUND, String.valueOf(userId)))
        );
    }

    @Transactional
    public User updateUser(UpdateUserCommand command) {
        User user = this.getUserById(command.userId());
        user.update(command);
        return from(userRepository.save(user.toEntity()));
    }

    @Transactional
    public User deleteUser(long userId) {
        return User.fromDelete(userRepository.deleteById(userId));
    }

    public int initUsers() {
        Users users = Users.from(userRepository.findByLogin(true));
        users.getItems().forEach(user -> userRepository.save(user.entityToInit()));
        return users.size();
    }

    @Transactional(readOnly = true)
    public User getUserByName(String userName) {
        return from(userRepository.findByName(userName)
                .orElseThrow(() -> new UserException(ExceptionResponseCode.USER_NOT_FOUND))
        );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User confirmLogin(long userId) {
        User user = this.getUserById(userId);
        if (user.isLogin()) {
            throw new UserException(ExceptionResponseCode.USER_ALREADY_LOGGED_IN);
        }
        return from(userRepository.save(user.entityToSession(true)));
    }

    public User confirmLogOut(long userId) {
        User user = this.getUserById(userId);
        return from(userRepository.save(user.entityToSession(false)));
    }

    public User confirmEnterGame(long userId) {
        User user = this.getUserById(userId);
        return from(userRepository.save(user.entityToGame(true)));
    }

    public User confirmExitGame(long userId) {
        User user = this.getUserById(userId);
        return from(userRepository.save(user.entityToGame(false)));
    }

    public List<String> retrieveWaitingList(RetrieveUserWaitingListCommand command) {
        NameGameStatuses nameGameStatuses = NameGameStatuses.from(userRepository.findByLogin(true));
        return nameGameStatuses.getWatingNameList();
    }

    public String getNameById(long userId) {
        User user = this.getUserById(userId);
        return user.getName();
    }

    public Map<Long, String> getNamesByIds(Set<Long> userIds) {
        if (userIds.isEmpty()) {
            return Collections.emptyMap();
        }
        return Users.getNames(userRepository.findNamesByIds(userIds));
    }

    public Map<Integer, String> getRelationType() {
        return RelationType.PLAYABLE.stream()
                .collect(Collectors.toMap(RelationType::ordinal, RelationType::getName,
                        (existing, replacement) -> existing, HashMap::new));
    }
}
