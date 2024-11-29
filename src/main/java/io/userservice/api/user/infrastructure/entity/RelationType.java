package io.userservice.api.user.infrastructure.entity;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 19.
 */
public enum RelationType {
    MODERATOR("사회자"),
    HOUSE_HOLD("식구"),
    FATHER_SIDE("친가"),
    MOTHER_SIDE("외가");

    @Getter
    private String name;

    public static final Set<RelationType> PLAYABLE;
    public static final Set<RelationType> PLAYABLE_EXCLUDE_HOUSE_HOLD;
    public static final Set<Integer> PLAYABLE_EXCLUDE_HOUSE_HOLD_ORDINAL;

    static {
        PLAYABLE = Stream.of(RelationType.values())
                .filter(relationType -> relationType != RelationType.MODERATOR)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        PLAYABLE_EXCLUDE_HOUSE_HOLD = Stream.of(RelationType.values())
                .filter(relationType -> relationType != RelationType.MODERATOR &&
                        relationType != RelationType.HOUSE_HOLD)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        PLAYABLE_EXCLUDE_HOUSE_HOLD_ORDINAL = PLAYABLE_EXCLUDE_HOUSE_HOLD.stream()
                .map(RelationType::ordinal)
                .collect(Collectors.toUnmodifiableSet());
    }

    RelationType(String name) {
        this.name = name;
    }

    public static RelationType from(int value) {
        RelationType[] types = RelationType.values();
        return types[value];
    }
}
