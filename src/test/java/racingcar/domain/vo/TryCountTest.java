package racingcar.domain.vo;

import org.junit.jupiter.api.Test;
import racingcar.exception.domain.InvalidDomainException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TryCountTest {
    @Test
    void 시도_횟수는_1_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> TryCount.of(0))
                .isInstanceOf(InvalidDomainException.class);
    }

    @Test
    void 시도_감소가_정상적으로_동작한다() {
        TryCount count = TryCount.of(3);
        TryCount decreased = count.decrease();
        assertThat(decreased.getValue()).isEqualTo(2);
    }
}