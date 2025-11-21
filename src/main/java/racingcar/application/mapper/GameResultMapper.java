package racingcar.application.mapper;

import racingcar.application.dto.response.GameResultResponseDTO;
import racingcar.application.dto.response.RoundResultResponseDTO;
import racingcar.domain.model.RacingGame.GameResult;
import java.util.List;

public class GameResultMapper {
    private final RoundResultMapper roundResultMapper = new RoundResultMapper();

    public GameResultResponseDTO toDTO(GameResult result) {
        List<RoundResultResponseDTO> history = result.getRoundHistory().stream()
                .map(roundResultMapper::toDTO)
                .toList();

        return new GameResultResponseDTO(
                history,
                result.getWinnerNames()
        );
    }
}
