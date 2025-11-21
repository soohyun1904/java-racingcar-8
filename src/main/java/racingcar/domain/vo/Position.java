package racingcar.domain.vo;

import racingcar.exception.domain.InvalidDomainException;
import java.util.Objects;
import static racingcar.exception.message.ErrorMessage.INVALID_POSITION;

public class Position implements Comparable<Position>{
    private static final int INITIAL_POSITION = 0;
    private static final int MOVE_DISTANCE = 1;

    private final int value;

    private Position(int value) {
        validate(value);
        this.value = value;
    }

    public static Position initial() {
        return new Position(INITIAL_POSITION);
    }

    public static Position of(int value) {
        return new Position(value);
    }

    private static void validate(int value) {
        if (value < 0) {
            throw new InvalidDomainException(INVALID_POSITION);
        }
    }

    public Position move() {
        return new Position(value + MOVE_DISTANCE);
    }

    @Override
    public int compareTo(Position other) {
        return Integer.compare(this.value, other.value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
