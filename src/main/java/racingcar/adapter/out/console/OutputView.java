package racingcar.adapter.out.console;

import racingcar.application.dto.response.CarStatusResponseDTO;
import racingcar.application.dto.response.GameResultResponseDTO;
import racingcar.application.dto.response.RoundResultResponseDTO;

import java.util.List;

public class OutputView {
    private static final String RESULT_HEADER = "\n실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String WINNER_DELIMITER = ", ";
    private static final String POSITION_SYMBOL = "-";
    private static final String NAME_POSITION_FORMAT = "%s : %s";

    public void printGameResult(GameResultResponseDTO result) {
        printResultHeader();
        printRoundResults(result.roundResults());
        printWinners(result.winnerNames());
    }

    private void printResultHeader() {
        System.out.println(RESULT_HEADER);
    }

    private void printRoundResults(List<RoundResultResponseDTO> roundResults) {
        for (RoundResultResponseDTO roundResult : roundResults) {
            printRoundResult(roundResult);
        }
    }

    private void printRoundResult(RoundResultResponseDTO roundResult) {
        for (CarStatusResponseDTO carStatus : roundResult.carStatuses()) {
            printCarStatus(carStatus);
        }
        System.out.println();
    }

    private void printCarStatus(CarStatusResponseDTO carStatus) {
        String position = POSITION_SYMBOL.repeat(carStatus.position());
        System.out.println(String.format(NAME_POSITION_FORMAT, carStatus.name(), position));
    }

    private void printWinners(List<String> winners) {
        String winnersText = String.join(WINNER_DELIMITER, winners);
        System.out.println(WINNER_PREFIX + winnersText);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
