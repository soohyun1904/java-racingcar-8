package racingcar.domain.vo;

import racingcar.exception.domain.InvalidDomainException;
import java.util.Objects;
import static racingcar.exception.message.ErrorMessage.INVALID_CAR_NAME_LENGTH;
import static racingcar.exception.message.ErrorMessage.INVALID_NULL;

public class Name {
    private static final int MAX_LENGTH = 5;

    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String value) {
        validate(value);
        return new Name(value);
    }

    private static void validate(String value) {
        validateNotEmpty(value);
        validateLength(value);
    }

    private static void validateLength(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new InvalidDomainException(INVALID_CAR_NAME_LENGTH);
        }
    }

    public static void validateNotEmpty(String value) {
        if (value == null) {
            throw new InvalidDomainException(INVALID_NULL);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
