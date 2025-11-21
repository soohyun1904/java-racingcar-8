package racingcar.infrastructure.policy;

import racingcar.domain.policy.MovePolicy;

public class FixedMovePolicy implements MovePolicy {
    private final boolean shouldMove;

    public FixedMovePolicy(boolean shouldMove) {
        this.shouldMove = shouldMove;
    }

    public static FixedMovePolicy alwaysMove() {
        return new FixedMovePolicy(true);
    }

    public static FixedMovePolicy neverMove() {
        return new FixedMovePolicy(false);
    }

    @Override
    public boolean shouldMove() {
        return shouldMove;
    }
}
