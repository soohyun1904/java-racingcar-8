package racingcar.config;

import racingcar.controller.RacingController;
import racingcar.service.RacingService;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {
    public InputView inputView(){
        return new InputView();
    }

    public OutputView outputView(){
        return new OutputView();
    }

    public NumberGenerator numberGenerator(){
        return new RandomNumberGenerator();
    }

    public RacingService racingService(){
        return new RacingService(numberGenerator());
    }

    public RacingController racingController(){
        return new RacingController(inputView(), outputView(), racingService());
    }
}
