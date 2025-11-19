package racingcar.exception.domain;

import racingcar.exception.message.ErrorMessage;
import static racingcar.exception.message.ErrorMessage.INVALID_VALUE;

public class InvalidDomainException extends IllegalArgumentException{
    public InvalidDomainException(){
        super(INVALID_VALUE.getMessage());
    }

    public InvalidDomainException(ErrorMessage message) {
        super(message.getMessage());
    }
}
