package racingcar.application.mapper;

import racingcar.application.dto.response.CarStatusResponseDTO;
import racingcar.domain.model.Cars.CarSnapshot;

public class CarStatusMapper {
    public CarStatusResponseDTO toDTO(CarSnapshot snapshot) {
        return new CarStatusResponseDTO(
                snapshot.name(),
                snapshot.position()
        );
    }
}
