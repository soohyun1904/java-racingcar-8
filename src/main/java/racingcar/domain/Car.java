package racingcar.domain;

public class Car {
    private final Name name;
    private  Position position;

    public Car(Name name) {
        this.name = name;
        this.position = new Position();
    }

    public void move(boolean canMove) {
        if (canMove) {
            this.position = position.move();
        }
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
