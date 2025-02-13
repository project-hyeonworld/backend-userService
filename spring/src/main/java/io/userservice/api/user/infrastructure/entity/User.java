package io.userservice.api.user.infrastructure.entity;

import io.userservice.api.postPosition.constant.PostpositionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    private RelationType relationType; //0 : 식구, 1 : 친가, 2 : 외가
    private String name;
    private PostpositionType postpositionType; //0 : 은 이, 1 : 는 가
    private String nickname;
    private PostpositionType nicknamePostpositionType;

    private Byte relation;
    private Boolean player;

    /*
    TODO:
     Session-based Management
      - Use a `Redis` to manage login states
      - Verify user status using session IDs without adding fields to `UserInfo`
     */
    private Boolean login;
    private Boolean inGame;

    @PrePersist
    protected void onCreate() {
        login = false;
        inGame = false;
        //proposition settings
    }

    public static UserBuilder defaultBuilder() {
        return User.builder();
    }
}