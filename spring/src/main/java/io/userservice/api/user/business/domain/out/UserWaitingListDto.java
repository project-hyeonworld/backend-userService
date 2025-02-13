package io.userservice.api.user.business.domain.out;

import java.util.List;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Getter
public class UserWaitingListDto {

    private final List<String> names;

    private UserWaitingListDto(List<String> names) {
        this.names = names;
    }

    public static UserWaitingListDto from(NameGameStatuses users) {
        return new UserWaitingListDto(users.getWatingNameList());
    }
}
