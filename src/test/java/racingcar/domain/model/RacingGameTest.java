package racingcar.domain.model;

import org.junit.jupiter.api.Test;
import racingcar.domain.policy.MovePolicy;
import racingcar.domain.vo.Name;
import racingcar.domain.vo.TryCount;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RacingGameTest {
    @Test
    void 게임은_지정된_라운드만큼_진행된다() {
        RacingGame game = RacingGame.of(
                List.of(Name.of("a"), Name.of("b")),
                TryCount.of(3)
        );

        MovePolicy always = () -> true;

        RacingGame.GameResult result = game.play(
                cnt -> List.of(always, always)
        );

        assertThat(result.getRoundHistory()).hasSize(3);
    }

    @Test
    void 우승자_계산_정확() {
        RacingGame game = RacingGame.of(
                List.of(Name.of("a"), Name.of("b")),
                TryCount.of(1)
        );

        RacingGame.GameResult result = game.play(
                cnt -> List.of(() -> true, () -> false)
        );

        assertThat(result.getWinnerNames()).containsExactly("a");
    }
}