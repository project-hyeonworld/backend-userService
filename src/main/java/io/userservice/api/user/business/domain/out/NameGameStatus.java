package io.userservice.api.user.business.domain.out;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */

record NameGameStatus(
        String name,
        boolean inGame
) {

    NameGameStatus(String name, boolean inGame) {
        this.name = name;
        this.inGame = inGame;
    }

    public static NameGameStatus from(String name, boolean inGame) {
        return new NameGameStatus(name, inGame);
    }

}
