package racingcar.domain;

import racingcar.exception.DomainValidationException;
import static racingcar.exception.message.ErrorMessage.INVALID_CAR_NAME_LENGTH;

public class Name {
    private static final int MAX_LENGTH = 5;
    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private void validate(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new DomainValidationException(INVALID_CAR_NAME_LENGTH);
        }
    }
}
