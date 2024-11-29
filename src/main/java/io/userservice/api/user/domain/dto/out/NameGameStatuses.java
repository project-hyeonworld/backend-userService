package io.userservice.api.user.domain.dto.out;

import io.userservice.api.user.infrastructure.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 12.
 */
public class NameGameStatuses {

    private List<NameGameStatus> nameGameStatuses;

    public NameGameStatuses(List<UserEntity> userEntities) {
        this.nameGameStatuses = new ArrayList<>();
        userEntities.stream()
                .peek(user -> this.nameGameStatuses.add(NameGameStatus.from(user.getName(), user.getInGame())));

    }

    public static NameGameStatuses from(List<UserEntity> userEntities) {
        return new NameGameStatuses(userEntities);
    }

    public List<String> getWatingNameList() {
        return nameGameStatuses.stream()
                .filter(nameGameStatus -> !nameGameStatus.inGame())
                .map(NameGameStatus::name)
                .toList();
    }
}
