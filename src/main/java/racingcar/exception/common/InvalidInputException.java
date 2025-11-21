package racingcar.exception.common;

import racingcar.exception.message.ErrorMessage;

import static racingcar.exception.message.ErrorMessage.INVALID_VALUE;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(){
        super(INVALID_VALUE.getMessage());
    }

    public InvalidInputException(ErrorMessage message) {
        super(message.getMessage());
    }
}
