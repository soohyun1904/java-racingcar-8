package racingcar.domain.model;

import racingcar.domain.policy.MovePolicy;
import racingcar.domain.vo.Name;
import racingcar.domain.vo.TryCount;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RacingGame {
    private final Cars initialCars;
    private final TryCount totalTryCount;
    private final List<Cars> history;

    private RacingGame(Cars initialCars, TryCount totalTryCount) {
        this.initialCars = Objects.requireNonNull(initialCars);
        this.totalTryCount = Objects.requireNonNull(totalTryCount);
        this.history = new ArrayList<>();
    }

    public static RacingGame of(List<Name> carNames, TryCount tryCount) {
        Cars cars = Cars.from(carNames);
        return new RacingGame(cars, tryCount);
    }

    public GameResult play(MovePolicySupplier movePolicySupplier) {
        Objects.requireNonNull(movePolicySupplier);

        Cars currentCars = initialCars;
        TryCount count = totalTryCount;

        while (count.hasRemaining()) {
            List<MovePolicy> movePolicies = movePolicySupplier.supply(currentCars.size());
            currentCars = currentCars.moveAll(movePolicies);
            history.add(currentCars);

            count = count.decrease();
        }

        return new GameResult(List.copyOf(history), currentCars.getWinnerNames());
    }

    @FunctionalInterface
    public interface MovePolicySupplier {
        List<MovePolicy> supply(int count);
    }

    public static final class GameResult {
        private final List<Cars> roundHistory;
        private final List<String> winnerNames;

        private GameResult(List<Cars> roundHistory, List<String> winnerNames) {
            this.roundHistory = roundHistory;
            this.winnerNames = winnerNames;
        }

        public List<Cars> getRoundHistory() {
            return roundHistory;
        }

        public List<String> getWinnerNames() {
            return winnerNames;
        }
    }
}
