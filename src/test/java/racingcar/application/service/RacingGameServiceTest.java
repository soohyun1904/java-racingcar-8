package racingcar.application.service;

import org.junit.jupiter.api.Test;
import racingcar.application.dto.response.GameResultResponseDTO;
import racingcar.application.port.in.GamePlayCommand;
import racingcar.domain.policy.MovePolicy;
import java.util.List;
import java.util.function.Supplier;
import static org.assertj.core.api.Assertions.assertThat;


class RacingGameServiceTest {

    @Test
    void 서비스는_도메인을_실행하고_DTO로_변환한다() {
        Supplier<MovePolicy> alwaysMove = () -> () -> true;

        RacingGameService service = new RacingGameService(alwaysMove);

        GamePlayCommand command = new GamePlayCommand(
                List.of("pobi", "java"),
                2
        );

        GameResultResponseDTO result = service.play(command);

        assertThat(result.roundResults()).hasSize(2);
        assertThat(result.winnerNames()).containsExactly("pobi", "java");
    }
}