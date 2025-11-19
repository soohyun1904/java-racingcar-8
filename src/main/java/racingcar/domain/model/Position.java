package racingcar.domain.model;

public class Position implements Comparable<Position>{
    private static final int INITIAL_POSITION = 0;
    private static final int MOVE_DISTANCE = 1;

    private final int value;

    public Position() {
        this(INITIAL_POSITION);
    }

    private Position(int value) {
        this.value = value;
    }

    public Position move() {
        return new Position(value + MOVE_DISTANCE);
    }

    @Override
    public int compareTo(Position other) {
        return Integer.compare(this.value, other.value);
    }

    public boolean isSamePosition(Position position) {
        return this.value == position.value;
    }

    public boolean isAhead(Position position) {
        return this.value > position.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return value == position.value;
    }
}
