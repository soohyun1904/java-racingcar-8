package racingcar.application.service.domainfactory;

import racingcar.domain.vo.Name;
import racingcar.domain.model.RacingGame;
import racingcar.domain.vo.TryCount;

import java.util.List;

public class GameDomainFactory {
    public RacingGame create(List<String> carNames, int tryCount) {

        List<Name> names = carNames.stream()
                .map(Name::of)
                .toList();

        TryCount count = TryCount.of(tryCount);

        return RacingGame.of(names, count);
    }
}
