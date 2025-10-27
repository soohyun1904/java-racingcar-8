package racingcar.view;

import racingcar.dto.CarStatusDto;
import racingcar.dto.RoundResultDto;
import java.util.List;

public class OutputView {
    private static final String RESULT_HEADER = "실행결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String WINNER_DELIMITER = ", ";
    private static final String POSITION_SYMBOL = "-";
    private static final String NAME_POSITION_DELIMITER = " : ";

    public void printRoundResult(List<RoundResultDto> roundResults){
        System.out.println(RESULT_HEADER);
        for (RoundResultDto roundResult : roundResults) {
            printCarPositions(roundResult.getCarStatuses());
        }
    }

    public void printWinners(List<String> winners){
        System.out.println(WINNER_PREFIX + String.join(WINNER_DELIMITER, winners));
    }


    private void printCarPositions(List<CarStatusDto> carStatuses){
        for (CarStatusDto carStatus : carStatuses) {
            System.out.println(carStatus.name() + NAME_POSITION_DELIMITER + POSITION_SYMBOL.repeat(carStatus.position()));
        }
        System.out.println();
    }

}
