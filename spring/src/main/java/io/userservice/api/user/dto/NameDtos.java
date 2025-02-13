package io.userservice.api.user.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 12.
 */
public record NameDtos(
        List<NameDto> nameDtos
) {

    public NameDtos(List<NameDto> nameDtos) {
        this.nameDtos = nameDtos;
    }

    public static NameDtos from(Map<Long, String> winnersIdName) {
        List<NameDto> nameDtos = winnersIdName.entrySet().stream()
                .map(entry -> NameDto.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return new NameDtos(nameDtos);
    }
}
