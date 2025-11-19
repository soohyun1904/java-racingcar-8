package racingcar.domain.model;

import racingcar.exception.domain.InvalidDomainException;
import static racingcar.exception.message.ErrorMessage.INVALID_CAR_NAME_LENGTH;

public class Name {
    private static final int MAX_LENGTH = 5;

    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        validateLength(value);
    }

    private void validateLength(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new InvalidDomainException(INVALID_CAR_NAME_LENGTH);
        }
    }

    public String getValue() {
        return value;
    }
}
