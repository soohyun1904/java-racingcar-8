package racingcar.infrastructure.policy;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.domain.policy.MovePolicy;

public class RandomMovePolicy implements MovePolicy {
    private static final int MIN_RANDOM_VALUE = 0;
    private static final int MAX_RANDOM_VALUE = 9;
    private static final int MOVE_THRESHOLD = 4;

    @Override
    public boolean shouldMove() {
        int randomValue = Randoms.pickNumberInRange(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
        return randomValue >= MOVE_THRESHOLD;
    }
}
