package racingcar.dto;

import racingcar.domain.Cars;
import java.util.List;

public class RoundResultDto {
    private final List<CarStatusDto> carStatuses;

    private RoundResultDto(List<CarStatusDto> carStatuses) {
        this.carStatuses = carStatuses;
    }

    public static RoundResultDto from(Cars cars){
        List<CarStatusDto> carStatuses = cars.getCars().stream()
                .map(CarStatusDto::from)
                .toList();
        return new RoundResultDto(carStatuses);
    }

    public List<CarStatusDto> getCarStatuses() {
        return carStatuses;
    }
}
