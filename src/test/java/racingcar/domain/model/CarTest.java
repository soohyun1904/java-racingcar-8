package racingcar.domain.model;

import org.junit.jupiter.api.Test;
import racingcar.domain.policy.MovePolicy;
import racingcar.domain.vo.Name;
import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

        @Test
        void 움직임_정책이_true이면_전진한다() {
            Car car = Car.of(Name.of("pobi"));
            MovePolicy always = () -> true;
            Car moved = car.move(always);
            assertThat(moved.getPositionValue()).isEqualTo(1);
        }

        @Test
        void 움직임_정책이_false이면_멈춘다() {
            Car car = Car.of(Name.of("pobi"));
            MovePolicy never = () -> false;
            Car moved = car.move(never);
            assertThat(moved.getPositionValue()).isEqualTo(0);
        }
}

