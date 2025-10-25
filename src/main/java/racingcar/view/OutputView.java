package racingcar.view;

import racingcar.dto.CarStatusDto;

import java.util.List;

public class OutputView {
    private static final String RESULT_HEADER = "실행결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String WINNER_DELIMITER = ", ";

    public void printResultHeader(){
        System.out.println(RESULT_HEADER);
    }

    public void printRoundResult(List<CarStatusDto> statuses){
        for (CarStatusDto status : statuses) {
            System.out.println(status.toDisplayString());
        }
        System.out.println();
    }

    public void printWinners(List<String> winners){
        System.out.println(WINNER_PREFIX + String.join(WINNER_DELIMITER, winners));
    }
}
