package racingcar.exception;

import racingcar.exception.message.ErrorMessage;
import static racingcar.exception.message.ErrorMessage.*;

public class DomainValidationException extends IllegalArgumentException {
    public DomainValidationException(){
        super(INVALID_VALUE.getMessage());
    }

    public DomainValidationException(ErrorMessage message) {
        super(message.getMessage());
    }
}
