package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.dto.CarStatusDto;
import racingcar.service.RacingService;
import racingcar.util.IntegerParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import java.util.List;

public class RacingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingService racingService;

    public RacingController(InputView inputView, OutputView outputView, RacingService racingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingService = racingService;
    }

    public void run(){
        String carNames = inputView.inputCarNames();
        int tryCount = IntegerParser.parse(inputView.inputTryCount());

        Cars cars = racingService.createCars(carNames);

        outputView.printResultHeader();
        for (int i = 0; i < tryCount; i++) {
            racingService.playRound(cars);
            List<CarStatusDto> statuses = racingService.getCarStatuses(cars);
            outputView.printRoundResult(statuses);
        }

        List<String> winners = racingService.findWinners(cars);
        outputView.printWinners(winners);
    }
}
