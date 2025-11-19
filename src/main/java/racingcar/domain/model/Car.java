package racingcar.domain.model;

import racingcar.domain.policy.MovePolicy;

public class Car {
    private final Name name;
    private final Position position;

    public Car(Name name) {
        this(name, new Position());
    }

    private Car(Name name, Position position) {
        this.name = name;
        this.position = position;
    }

    public Car move(MovePolicy movePolicy) {
        if (movePolicy.shouldMove()) {
            return new Car(name, position.move());
        }
        return this;
    }

    public boolean isAheadOf(Car other) {
        return this.position.isAhead(other.position);
    }

    public boolean isSamePositionWith(Car other) {
        return this.position.isSamePosition(other.position);
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public String getNameValue() {
        return name.getValue();
    }

    public int getPositionValue() {
        return position.getValue();
    }
}
