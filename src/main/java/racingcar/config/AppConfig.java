package racingcar.config;

import racingcar.adapter.in.console.InputConverter;
import racingcar.adapter.in.console.InputHandler;
import racingcar.application.port.in.PlayGameUseCase;
import racingcar.application.service.RacingGameService;
import racingcar.domain.policy.MovePolicy;
import racingcar.infrastructure.policy.RandomMovePolicy;
import racingcar.adapter.in.controller.RacingGameController;
import racingcar.adapter.in.console.InputView;
import racingcar.adapter.out.console.OutputView;

import java.util.function.Supplier;

public class AppConfig {
    public RacingGameController racingGameController() {
        return new RacingGameController(
                inputHandler(),
                outputView(),
                playGameUseCase()
        );
    }

    private PlayGameUseCase playGameUseCase() {
        return new RacingGameService(randomMovePolicySupplier());
    }

    private Supplier<MovePolicy> randomMovePolicySupplier() {
        return RandomMovePolicy::new;
    }

    private InputHandler inputHandler(){
        return new InputHandler(inputView(), inputConverter());
    }

    private InputConverter inputConverter(){
        return new InputConverter();
    }
    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
