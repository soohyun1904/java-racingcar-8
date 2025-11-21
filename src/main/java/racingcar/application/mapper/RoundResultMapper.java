package racingcar.application.mapper;

import racingcar.application.dto.response.CarStatusResponseDTO;
import racingcar.application.dto.response.RoundResultResponseDTO;
import racingcar.domain.model.Cars;
import java.util.List;

public class RoundResultMapper {
    private final CarStatusMapper statusMapper = new CarStatusMapper();

    public RoundResultResponseDTO toDTO(Cars cars) {
        List<CarStatusResponseDTO> statuses = cars.getSnapshots().stream()
                .map(statusMapper::toDTO)
                .toList();

        return new RoundResultResponseDTO(statuses);
    }
}
