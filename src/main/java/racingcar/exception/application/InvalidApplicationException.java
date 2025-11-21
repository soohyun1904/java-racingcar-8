package racingcar.exception.application;

import racingcar.exception.message.ErrorMessage;
import static racingcar.exception.message.ErrorMessage.INVALID_VALUE;

public class InvalidApplicationException extends IllegalArgumentException {
    public InvalidApplicationException() {
      super(INVALID_VALUE.getMessage());
    }

  public InvalidApplicationException(ErrorMessage message) {
    super(message.getMessage());
  }
}
