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

    private List<Car> createCars(List<Name> names) {
        validateDuplicate(names);

        List<Car> cars = new ArrayList<>();
        for (Name name : names) {
            cars.add(new Car(name));
        }
        return cars;
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
