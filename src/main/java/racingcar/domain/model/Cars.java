package racingcar.domain.model;

import racingcar.domain.policy.MovePolicy;
import racingcar.exception.domain.InvalidDomainException;
import racingcar.exception.message.ErrorMessage;

import java.util.*;

import static racingcar.exception.message.ErrorMessage.*;

public class Cars {
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = List.copyOf(cars);
    }

    public static Cars from(List<Name> names) {
        validateDuplicates(names);
        List<Car> carList = createCars(names);
        return new Cars(carList);
    }

    private static void validateDuplicates(List<Name> names) {
        Set<Name> uniqueNames = new HashSet<>(names);
        if (uniqueNames.size() != names.size()) {
            throw new InvalidDomainException(DUPLICATE_CAR_NAME);
        }
    }

    private static List<Car> createCars(List<Name> names) {
        return names.stream()
                .map(Car::new)
                .toList();
    }

    public Cars moveAll(List<MovePolicy> movePolicies) {
        validatePolicyCount(movePolicies);

        List<Car> movedCars = new ArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            Car movedCar = cars.get(i).move(movePolicies.get(i));
            movedCars.add(movedCar);
        }

        return new Cars(movedCars);
    }

    private void validatePolicyCount(List<MovePolicy> movePolicies) {
        if (movePolicies.size() != cars.size()) {
            throw new InvalidDomainException(POLICY_COUNT_MISMATCH);
        }
    }

    public List<Car> findWinners() {
        Position maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition().equals(maxPosition))
                .toList();
    }

    private Position findMaxPosition() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Position::compareTo)
                .orElseThrow(() -> new InvalidDomainException(CARS_EMPTY));
    }

    public int size() {
        return cars.size();
    }
}
