package racingcar.domain.model;

import org.junit.jupiter.api.Test;
import racingcar.domain.policy.MovePolicy;
import racingcar.domain.vo.Name;
import racingcar.exception.domain.InvalidDomainException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {
    @Test
    void 중복된_이름이_있으면_예외가_발생한다() {
        List<Name> names = List.of(Name.of("a"), Name.of("a"));
        assertThatThrownBy(() -> Cars.from(names))
                .isInstanceOf(InvalidDomainException.class);
    }

    @Test
    void 정책_개수와_자동차수_불일치_시_예외() {
        Cars cars = Cars.from(List.of(Name.of("a"), Name.of("b")));
        List<MovePolicy> policies = List.of(() -> true); // 1개만

        assertThatThrownBy(() -> cars.moveAll(policies))
                .isInstanceOf(InvalidDomainException.class);
    }

    @Test
    void 우승자_판별이_정확하다() {
        Cars cars = Cars.from(List.of(Name.of("a"), Name.of("b")));
        List<MovePolicy> policies = List.of(() -> true, () -> false);
        Cars moved = cars.moveAll(policies);
        assertThat(moved.getWinnerNames()).containsExactly("a");
    }
}