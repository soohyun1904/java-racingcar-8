package racingcar.application.port.in;

import racingcar.application.dto.response.GameResultResponseDTO;

public interface PlayGameUseCase {
    GameResultResponseDTO play(GamePlayCommand command);
}
