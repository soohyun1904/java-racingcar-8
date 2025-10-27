package racingcar.domain;

import racingcar.exception.DomainValidationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static racingcar.exception.message.ErrorMessage.DUPLICATE_CAR_NAME;

public class Cars{
    private final List<Car> cars;

    public Cars(List<Name> names) {
        this.cars = createCars(names);
    }

    public List<Car> getCars(){
        return cars;
    }
    public int size() {
        return cars.size();
    }

    public void moveAll(List<Integer> randomNumbers){
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).move(randomNumbers.get(i));
        }
    }

    public List<Car> findCarsWithMaxPosition(){
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.isAtPosition(maxPosition))
                .toList();
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPositionValue)
                .max()
                .orElse(0);
    }

    private List<Car> createCars(List<Name> names) {
        validateDuplicate(names);

        List<Car> cars = new ArrayList<>();
        for (Name name : names) {
            cars.add(new Car(name));
        }
        return List.copyOf(cars);
    }

    private void validateDuplicate(List<Name> names) {
       Set<String> uniqueNames = new HashSet<>();
        for (Name name : names) {
            if(isDuplicate(name, uniqueNames)){
                throw new DomainValidationException(DUPLICATE_CAR_NAME);
            }
        }
    }

    private boolean isDuplicate(Name name, Set<String> uniqueNames) {
        return !uniqueNames.add(name.getValue());
    }
}
