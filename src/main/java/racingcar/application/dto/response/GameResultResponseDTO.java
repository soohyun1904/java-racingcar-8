package racingcar.application.dto.response;

import racingcar.exception.application.InvalidApplicationException;

import java.util.List;

import static racingcar.exception.message.ErrorMessage.NULL_RESULT_DATA;

public record GameResultResponseDTO(
        List<RoundResultResponseDTO> roundResults,
        List<String> winnerNames
) {
    public GameResultResponseDTO {
        if (roundResults == null || winnerNames == null) {
            throw new InvalidApplicationException(NULL_RESULT_DATA);
        }

        roundResults = List.copyOf(roundResults);
        winnerNames = List.copyOf(winnerNames);
    }
}
