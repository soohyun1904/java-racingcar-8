package racingcar.domain;

public class Car {
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
        if (randomNumber >= 4) {
            this.position = position.move();
        }
    }

    public boolean isAtPosition(int position){
        return this.getPositionValue() == position;
    }
}
