package racingcar.dto;

import java.util.List;

public class GameResultDto {
    private final List<RoundResultDto> roundResults;
    private final List<String> winnerCarNames;

    private GameResultDto(List<RoundResultDto> roundResults, List<String> winnerCarNames) {
        this.roundResults = roundResults;
        this.winnerCarNames = winnerCarNames;
    }

    public static GameResultDto from(List<RoundResultDto> roundResults, List<String> winnerCarNames) {
        return new GameResultDto(roundResults, winnerCarNames);
    }

    public List<RoundResultDto> getRoundResults() {
        return roundResults;
    }

    public List<String> getWinnerCarNames(){
        return winnerCarNames;
    }
}
