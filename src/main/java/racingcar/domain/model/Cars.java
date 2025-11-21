package racingcar.domain.model;

import racingcar.domain.policy.MovePolicy;
import racingcar.domain.vo.Name;
import racingcar.domain.vo.Position;
import racingcar.exception.domain.InvalidDomainException;
import java.util.*;
import static racingcar.exception.message.ErrorMessage.*;

public class Cars {
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = List.copyOf(cars);
    }

    public static Cars from(List<Name> names) {
        validate(names);
        List<Car> carList = createCars(names);
        return new Cars(carList);
    }

    private static void validate(List<Name> names){
        validateNotEmpty(names);
        validateDuplicates(names);
    }

    private static void validateNotEmpty(List<Name> names) {
        if (names == null || names.isEmpty()) {
            throw new InvalidDomainException(CARS_EMPTY);
        }
    }

    private static void validateDuplicates(List<Name> names) {
        Set<Name> uniqueNames = new HashSet<>(names);
        if (uniqueNames.size() != names.size()) {
            throw new InvalidDomainException(DUPLICATE_CAR_NAME);
        }
    }

    private static List<Car> createCars(List<Name> names) {
        return names.stream()
                .map(Car::of)
                .toList();
    }

    public Cars moveAll(List<MovePolicy> movePolicies) {
        validatePolicyCount(movePolicies);

        List<Car> movedCars = new ArrayList<>(cars.size());
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

    public List<String> getWinnerNames() {
        Position maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition().equals(maxPosition))
                .map(Car::getNameValue)
                .toList();
    }

    private Position findMaxPosition() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Position::compareTo)
                .orElseThrow(() -> new InvalidDomainException(CARS_EMPTY));
    }

    public List<CarSnapshot> getSnapshots() {
        return cars.stream()
                .map(car -> new CarSnapshot(car.getNameValue(), car.getPositionValue()))
                .toList();
    }

    public record CarSnapshot(String name, int position) {}

    public int size() {
        return cars.size();
    }
}
