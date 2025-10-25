package racingcar.service;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.dto.CarStatusDto;
import racingcar.util.NameParser;
import racingcar.util.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class RacingService {
    private final NumberGenerator numberGenerator;

    public RacingService(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Cars createCars(String carNames){
        List<Name> names = NameParser.splitWithDelimiter(carNames);
        return new Cars(names);
    }

    public void playRound(Cars cars){
        int carCount = cars.size();
        List<Integer> randomNumbers = generateRandomNumbers(carCount);
        cars.moveAll(randomNumbers);
    }

    public List<CarStatusDto> getCarStatuses(Cars cars) {
        return cars.getCars().stream()
                .map(CarStatusDto::from)
                .toList();
    }

    public List<String> findWinners(Cars cars){
        return cars.findCarsWithMaxPosition().stream()
                .map(Car::getNameValue)
                .toList();
    }

    private List<Integer> generateRandomNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(numberGenerator.generate());
        }
        return numbers;
    }
}
