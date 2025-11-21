package racingcar.domain.model;

import racingcar.domain.policy.MovePolicy;
import racingcar.domain.vo.Name;
import racingcar.domain.vo.Position;
import java.util.Objects;

public class Car {
    private final Name name;
    private final Position position;

    private Car(Name name, Position position) {
        this.name = Objects.requireNonNull(name);
        this.position = Objects.requireNonNull(position);
    }

    public static Car of(Name name) {
        return new Car(name, Position.initial());
    }

    public static Car of(Name name, Position position) {
        return new Car(name, position);
    }

    public Car move(MovePolicy movePolicy) {
        Objects.requireNonNull(movePolicy);
        if (movePolicy.shouldMove()) {
            return new Car(name, position.move());
        }
        return this;
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
