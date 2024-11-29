package io.userservice.api.user.dto;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 12.
 */
public record NameDto(
        long id,
        String name
) {

    public NameDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static NameDto from(long id, String name) {
        return new NameDto(id, name);
    }

}
