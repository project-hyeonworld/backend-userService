package io.userservice.api.user.domain.dto.out;

import java.util.Objects;
import lombok.Getter;
import io.userservice.common.exception.ExceptionResponseCode;
import io.userservice.common.exception.ServerException;
import io.userservice.api.postPosition.constant.PostpositionType;
import io.userservice.api.user.domain.dto.in.CreateUserCommand;
import io.userservice.api.user.domain.dto.in.UpdateUserCommand;
import io.userservice.api.user.infrastructure.entity.RelationType;

import io.userservice.common.mapper.ObjectrMapper;
import io.userservice.api.user.infrastructure.entity.UserEntity;



/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Getter
public class User {

    Long id;
    String name;
    RelationType relationType;
    String nickname;
    Byte relation;
    boolean login;
    boolean inGame;

    private UserEntity.UserEntityBuilder initializeEntity() {
        return UserEntity.builder()
                .id(this.id)
                .name(this.name)
                .relationType(this.relationType)
                .nickname(this.nickname)
                .login(this.login)
                .inGame(this.inGame)
                .relation(this.relation);
    }

    public static User from(UserEntity userEntity) {
        return ObjectrMapper.convert(userEntity, User.class);
    }

    public static User fromDelete(int ret) {
        if (ret == 1) {
            return new User();
        }
        throw new ServerException(ExceptionResponseCode.USER_NOT_FOUND);
    }

    public static UserEntity toEntity(CreateUserCommand command, PostpositionType josa) {
        return UserEntity.defaultBuilder()
                .name(command.name())
                .relationType(RelationType.from(command.relationType()))
                .postpositionType(josa)
                .relation(command.relation())
                .build();
    }

    public UserEntity toEntity() {
        return initializeEntity()
                .build();
    }

    public UserEntity entityToInit() {
        return initializeEntity()
                .login(false)
                .inGame(false)
                .build();
    }

    public UserEntity entityToSession(boolean login) {
        return initializeEntity()
                .login(login)
                .inGame(false)
                .build();
    }

    public UserEntity entityToGame(boolean inGame) {
        return initializeEntity()
                .login(true)
                .inGame(inGame)
                .build();
    }

    public void update(UpdateUserCommand command) {
        command.name().ifPresent(newName -> {
            if (!Objects.equals(this.name, newName)) {
                this.name = newName;
            }
        });

        command.relationType().ifPresent(newRelationType -> {
            RelationType enumType = RelationType.from(newRelationType);
            if (this.relationType != enumType) {
                this.relationType = enumType;
            }
        });

        command.relation().ifPresent(newRelation -> {
            if (this.relation != newRelation) {
                this.relation = newRelation;
            }
        });
    }

    ;
}
