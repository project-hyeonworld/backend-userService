package io.userservice.api.user.domain;

import static io.userservice.api.user.domain.dto.out.UserInfo.*;
import static io.userservice.api.user.domain.dto.out.UserInfos.*;

import io.userservice.api.user.domain.dto.out.UserInfo;
import io.userservice.common.exception.ExceptionResponseCode;
import io.userservice.common.exception.UserException;
import io.userservice.api.postPosition.PostpositionService;
import io.userservice.api.user.domain.dto.in.CreateUserCommand;
import io.userservice.api.user.domain.dto.in.RetrieveUserWaitingListCommand;
import io.userservice.api.user.domain.dto.in.UpdateUserCommand;
import io.userservice.api.user.domain.dto.out.NameGameStatuses;
import io.userservice.api.user.domain.dto.out.UserInfos;
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

    public UserInfo createUser(CreateUserCommand command) {

        return from(userRepository.save(toEntity(command, postpositionService.josa(command.name()))));
    }

    public UserInfos getAllUsers() {
        return from(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    public UserInfo getUserById(long userId) {
        return from(userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ExceptionResponseCode.USER_NOT_FOUND, String.valueOf(userId)))
        );
    }

    @Transactional
    public UserInfo updateUser(UpdateUserCommand command) {
        UserInfo userInfo = this.getUserById(command.userId());
        userInfo.update(command);
        return from(userRepository.save(userInfo.toEntity()));
    }

    @Transactional
    public UserInfo deleteUser(long userId) {
        return UserInfo.fromDelete(userRepository.deleteById(userId));
    }

    public int initUsers() {
        UserInfos userInfos = UserInfos.from(userRepository.findByLogin(true));
        userInfos.getItems().forEach(userInfo -> userRepository.save(userInfo.entityToInit()));
        return userInfos.size();
    }

    @Transactional(readOnly = true)
    public UserInfo getUserByName(String userName) {
        return from(userRepository.findByName(userName)
                .orElseThrow(() -> new UserException(ExceptionResponseCode.USER_NOT_FOUND))
        );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserInfo confirmLogin(long userId) {
        UserInfo userInfo = this.getUserById(userId);
        if (userInfo.isLogin()) {
            throw new UserException(ExceptionResponseCode.USER_ALREADY_LOGGED_IN);
        }
        return from(userRepository.save(userInfo.entityToSession(true)));
    }

    public UserInfo confirmLogOut(long userId) {
        UserInfo userInfo = this.getUserById(userId);
        return from(userRepository.save(userInfo.entityToSession(false)));
    }

    public UserInfo confirmEnterGame(long userId) {
        UserInfo userInfo = this.getUserById(userId);
        return from(userRepository.save(userInfo.entityToGame(true)));
    }

    public UserInfo confirmExitGame(long userId) {
        UserInfo userInfo = this.getUserById(userId);
        return from(userRepository.save(userInfo.entityToGame(false)));
    }

    public List<String> retrieveWaitingList(RetrieveUserWaitingListCommand command) {
        NameGameStatuses nameGameStatuses = NameGameStatuses.from(userRepository.findByLogin(true));
        return nameGameStatuses.getWatingNameList();
    }

    public String getNameById(long userId) {
        UserInfo userInfo = this.getUserById(userId);
        return userInfo.getName();
    }

    public Map<Long, String> getNamesByIds(Set<Long> userIds) {
        if (userIds.isEmpty()) {
            return Collections.emptyMap();
        }
        return UserInfos.getNames(userRepository.findNamesByIds(userIds));
    }

    public Map<Integer, String> getRelationType() {
        return RelationType.PLAYABLE.stream()
                .collect(Collectors.toMap(RelationType::ordinal, RelationType::getName,
                        (existing, replacement) -> existing, HashMap::new));
    }
}
