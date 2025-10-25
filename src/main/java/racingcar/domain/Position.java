package racingcar.domain;

public class Position {
    private static final int INITIAL_POSITION = 0;
    private static final int MOVE_DISTANCE = 1;

    private final int value;

    public Position() {
        this(INITIAL_POSITION);
    }

    private Position(int value) {
        this.value = value;
    }

    public Position move(){
        return new Position(value + MOVE_DISTANCE);
    }

    public int getValue(){
        return value;
    }
}
