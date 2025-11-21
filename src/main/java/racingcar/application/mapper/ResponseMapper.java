package racingcar.application.mapper;

import racingcar.application.dto.response.GameResultResponseDTO;
import racingcar.domain.model.RacingGame.GameResult;

public class ResponseMapper {
    private final GameResultMapper gameResultMapper = new GameResultMapper();

    public GameResultResponseDTO toDTO(GameResult result) {
        return gameResultMapper.toDTO(result);
    }
}
