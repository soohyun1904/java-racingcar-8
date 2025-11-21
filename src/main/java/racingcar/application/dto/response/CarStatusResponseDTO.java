package racingcar.application.dto.response;

import racingcar.exception.application.InvalidApplicationException;

import static racingcar.exception.message.ErrorMessage.EMPTY_NAME;
import static racingcar.exception.message.ErrorMessage.NEGATIVE_POSITION;

public record CarStatusResponseDTO(
        String name,
        int position
) {
    public CarStatusResponseDTO {
        if (name == null || name.isBlank()) {
            throw new InvalidApplicationException(EMPTY_NAME);
        }
        if (position < 0) {
            throw new InvalidApplicationException(NEGATIVE_POSITION);
        }
    }
}
