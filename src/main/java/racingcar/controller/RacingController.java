package racingcar.controller;

import racingcar.dto.GameResultDto;
import racingcar.service.RacingService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

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
        GameResultDto gameResultDto = racingService.runGame(carNames, tryCount);
        outputView.printRoundResult(gameResultDto.getRoundResults());
        outputView.printWinners(gameResultDto.getWinnerCarNames());
    }
}
