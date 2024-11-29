package io.userservice.api.postPosition;

import io.userservice.api.postPosition.constant.PostpositionType;
import org.springframework.stereotype.Service;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 21.
 */
@Service
public class PostpositionService {

    public PostpositionType josa(String word) {
        char lastCharacter = word.charAt(word.length() - 1);
        if (hasLastConsonant(lastCharacter)) {
            return PostpositionType.은_이;
        }
        return PostpositionType.는_가;
    }

    private boolean hasLastConsonant(char lastCharacter) {
        return (lastCharacter - 0XAC00) % 28 > 0;
    }
}
