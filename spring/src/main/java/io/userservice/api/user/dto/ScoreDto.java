package io.userservice.api.user.dto;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */

public record ScoreDto(
        long userId,
        long score
) {

    public static ScoreDto from(Long userId, Long score) {
        return new ScoreDto(userId, score);
    }
}
