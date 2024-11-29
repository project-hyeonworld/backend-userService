package io.userservice.api.user.controller.dto.res;

import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 21.
 */
public record RelationTypeResponse(
        Map<Integer, String> relationTypes
) {

    public static RelationTypeResponse from(Map<Integer, String> relationTypeText) {
        return new RelationTypeResponse(relationTypeText);
    }
}
