package racingcar.util;

import racingcar.domain.Name;
import racingcar.exception.InputParsingException;
import java.util.List;
import static racingcar.exception.message.ErrorMessage.*;

public class InputValidator {
    public static void validateNamesInput(List<Name> input){
        if(input.isEmpty()){
            throw new InputParsingException(EMPTY_CAR_NAMES);
        }
    }

    public static void validateIntegerInput(String input){
        if(input.isBlank()){
            throw new InputParsingException(EMPTY_TRY_COUNT);
        }
    }

    public static void validatePositive(int value){
        if (value <= 0) {
            throw new InputParsingException(INVALID_TRY_COUNT);
        }
    }
}
