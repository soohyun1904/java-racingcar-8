package racingcar.adapter.in.controller;

import racingcar.adapter.in.console.InputHandler;
import racingcar.application.dto.response.GameResultResponseDTO;
import racingcar.application.port.in.GamePlayCommand;
import racingcar.application.port.in.PlayGameUseCase;
import racingcar.adapter.out.console.OutputView;
import java.util.List;

public class RacingGameController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final PlayGameUseCase playGameUseCase; // ← 여기 주목!

    public RacingGameController(
            InputHandler inputHandler,
            OutputView outputView,
            PlayGameUseCase playGameUseCase
    ) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.playGameUseCase = playGameUseCase;
    }

    public void run() {
        try {
            List<String> carNames = retry(inputHandler::readCarNameStrings);
            int tryCount = retry(inputHandler::readTryCountValue);
            GamePlayCommand command = new GamePlayCommand(carNames, tryCount);
            GameResultResponseDTO result = playGameUseCase.play(command);
            outputView.printGameResult(result);
        } catch (Exception e) {
            outputView.printError("게임 실행 중 오류 발생: " + e.getMessage());
            throw e;
        }
    }

    private <T> T retry(Task<T> task) {
        while (true) {
            try {
                return task.run();
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    @FunctionalInterface
    private interface Task<T> {
        T run();
    }
}
