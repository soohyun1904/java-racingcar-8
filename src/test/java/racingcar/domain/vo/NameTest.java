package racingcar.domain.vo;

import org.junit.jupiter.api.Test;
import racingcar.exception.domain.InvalidDomainException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {
    @Test
    void 이름은_5자를_초과하면_예외가_발생한다() {
        assertThatThrownBy(() -> Name.of("abcdef"))
                .isInstanceOf(InvalidDomainException.class);
    }

    @Test
    void 정상적으로_이름을_생성할_수_있다() {
        Name name = Name.of("pobi");
        assertThat(name.getValue()).isEqualTo("pobi");
    }
}