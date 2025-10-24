package racingcar.exception;

import racingcar.exception.message.ErrorMessage;
import static racingcar.exception.message.ErrorMessage.INVALID_VALUE;

public class InputParsingException extends IllegalArgumentException{
    public InputParsingException() {
        super(INVALID_VALUE.getMessage());
    }

    public InputParsingException(ErrorMessage message) {
        super(message.getMessage());
    }
}
