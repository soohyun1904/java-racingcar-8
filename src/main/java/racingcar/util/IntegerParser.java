package racingcar.util;

import racingcar.exception.InputParsingException;
import racingcar.exception.message.ErrorMessage;

import static racingcar.exception.message.ErrorMessage.*;
import static racingcar.util.InputValidator.validateIntegerInput;
import static racingcar.util.InputValidator.validatePositive;

public class IntegerParser {
    private IntegerParser(){
    }

    public static int parse(String input) {
        validateIntegerInput(input);
        try{
            int value = Integer.parseInt(input);
            validatePositive(value);
            return value;
        }catch(NumberFormatException e){
            throw new InputParsingException(INVALID_NUMBER_FORMAT);
        }
    }
}
