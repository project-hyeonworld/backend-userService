package io.userservice.api.user.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public class ScoreDtos extends ArrayList<ScoreDto> {

    public ScoreDtos(List<ScoreDto> scoreDtos) {
        super(scoreDtos != null ? scoreDtos : Collections.emptyList());
    }

    public static ScoreDtos from(Map<Long, Long> scoreHistories) {
        return new ScoreDtos(scoreHistories.entrySet().stream()
                .map(entry -> ScoreDto.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
    }

    public static HashMap<Long, Long> toSum(ScoreDtos scoreDtos) {
        HashMap<Long, Long> ret = new HashMap<>();
        for (ScoreDto info : scoreDtos) {
            ret.merge(info.userId(), info.score(), Long::sum);
        }
        return ret;
    }
}