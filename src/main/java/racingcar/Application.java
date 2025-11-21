package racingcar;

import racingcar.config.AppConfig;
import racingcar.adapter.in.controller.RacingGameController;

public class Application {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        RacingGameController controller = config.racingGameController();
        controller.run();
    }
}
