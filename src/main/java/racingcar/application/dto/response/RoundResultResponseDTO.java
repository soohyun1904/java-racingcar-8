package racingcar.application.dto.response;

import racingcar.exception.application.InvalidApplicationException;
import java.util.List;
import static racingcar.exception.message.ErrorMessage.*;

public record RoundResultResponseDTO(
        List<CarStatusResponseDTO> carStatuses
) {
    public RoundResultResponseDTO {
        if (carStatuses == null) {
            throw new InvalidApplicationException(NULL_CAR_STATUS_LIST);
        }
        carStatuses = List.copyOf(carStatuses);
    }
}
