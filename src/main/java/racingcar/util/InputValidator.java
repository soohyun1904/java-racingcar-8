package racingcar.util;

import racingcar.domain.Name;
import racingcar.exception.InputParsingException;
import java.util.List;
import static racingcar.exception.message.ErrorMessage.*;

public class InputValidator {
    private static final int MAX_LENGTH = 5;

    public static void validateNamesInput(List<Name> input){
        if(input.isEmpty()){
            throw new InputParsingException(EMPTY_CAR_NAMES);
        }
    }
}
