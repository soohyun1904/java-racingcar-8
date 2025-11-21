package racingcar.application.port.in;

import java.util.List;

public record GamePlayCommand(
        List<String> carNames,
        int tryCount
) {}
