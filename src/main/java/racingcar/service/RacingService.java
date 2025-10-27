package racingcar.service;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.dto.RoundResultDto;
import racingcar.util.IntegerParser;
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

    public List<RoundResultDto> playGame(Cars cars, String tryCount) {
        List<RoundResultDto> allRounds = new ArrayList<>();
        int count = IntegerParser.parse(tryCount);
        for (int i = 0; i < count; i++) {
            playRound(cars);
            RoundResultDto roundResultDto = RoundResultDto.from(cars);
            allRounds.add(roundResultDto);
        }
        return List.copyOf(allRounds);
    }

    private void playRound(Cars cars){
        int carCount = cars.size();
        List<Integer> randomNumbers = generateRandomNumbers(carCount);
        cars.moveAll(randomNumbers);
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
        return List.copyOf(numbers);
    }
}
