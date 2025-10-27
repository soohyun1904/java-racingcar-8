package racingcar.domain;

public class Car {
    private static final int MOVE_THRESHOLD = 4;

    private final Name name;
    private  Position position;

    public Car(Name name) {
        this.name = name;
        this.position = new Position();
    }

    public String getNameValue(){
        return name.getValue();
    }

    public int getPositionValue(){
        return position.getValue();
    }

    public void move(int randomNumber) {
        if (randomNumber >= MOVE_THRESHOLD) {
            this.position = position.move();
        }
    }

    public boolean isAtPosition(int position){
        return this.getPositionValue() == position;
    }
}
