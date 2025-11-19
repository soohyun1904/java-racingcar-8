package racingcar.domain.model;

import racingcar.domain.policy.MovePolicy;
import racingcar.domain.vo.TryCount;
import racingcar.exception.domain.InvalidDomainException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static racingcar.exception.message.ErrorMessage.INVALID_ROUND_NUMBER;

public class RacingGame {
    private final Cars initialCars;
    private final TryCount totalTryCount;
    private final List<Cars> history;

    public RacingGame(List<Name> carNames, TryCount tryCount) {
        this.initialCars = Cars.from(carNames);
        this.totalTryCount = tryCount;
        this.history = new ArrayList<>();
    }

    public GameResult play(MovePolicySupplier movePolicySupplier) {
        Cars currentCars = initialCars;

        for (int round = 0; round < totalTryCount.getValue(); round++) {
            List<MovePolicy> movePolicies = movePolicySupplier.supply(currentCars.size());
            currentCars = currentCars.moveAll(movePolicies);
            history.add(currentCars);
        }

        return new GameResult(Collections.unmodifiableList(history), currentCars.findWinners());
    }

    @FunctionalInterface
    public interface MovePolicySupplier {
        List<MovePolicy> supply(int count);
    }

    public static final class GameResult {
        private final List<Cars> roundHistory;
        private final List<Car> winners;

        private GameResult(List<Cars> roundHistory, List<Car> winners) {
            this.roundHistory = roundHistory;
            this.winners = winners;
        }

        public List<Cars> getRoundHistory() {
            return roundHistory;
        }

        public List<Car> getWinners() {
            return winners;
        }

        public Cars getRound(int roundNumber) {
            if (roundNumber < 1 || roundNumber > roundHistory.size()) {
                throw new InvalidDomainException(INVALID_ROUND_NUMBER);
            }
            return roundHistory.get(roundNumber - 1);
        }

        public int getTotalRounds() {
            return roundHistory.size();
        }
    }
}
