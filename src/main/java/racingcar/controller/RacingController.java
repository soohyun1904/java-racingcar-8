package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.dto.RoundResultDto;
import racingcar.service.RacingService;
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
        String tryCount = inputView.inputTryCount();
        Cars cars = racingService.createCars(carNames);
        List<RoundResultDto> roundResults = racingService.playGame(cars, tryCount);
        outputView.printRoundResult(roundResults);
        List<String> winners = racingService.findWinners(cars);
        outputView.printWinners(winners);
    }

}
