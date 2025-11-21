package racingcar.domain.vo;

import racingcar.exception.domain.InvalidDomainException;
import static racingcar.exception.message.ErrorMessage.INVALID_TRY_COUNT;

public class TryCount {
    private static final int MIN_COUNT = 1;

    private final int value;

    private TryCount(int value) {
        this.value = value;
    }

    public static TryCount of(int value) {
        validate(value);
        return new TryCount(value);
    }

    private static void validate(int value) {
        if (value < MIN_COUNT) {
            throw new InvalidDomainException(INVALID_TRY_COUNT);
        }
    }

    public TryCount decrease() {
        return new TryCount(value - 1);
    }

    public boolean hasRemaining() {
        return value > 0;
    }

    public int getValue() {
        return value;
    }
}
