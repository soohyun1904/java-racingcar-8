package racingcar.domain.vo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @Test
    void move는_값을_1_증가시킨다() {
        Position pos = Position.initial();
        Position moved = pos.move();
        assertThat(moved.getValue()).isEqualTo(1);
    }

    @Test
    void position은_비교가_가능하다() {
        Position a = Position.of(1);
        Position b = Position.of(3);
        assertThat(b).isGreaterThan(a);
    }

    @Test
    void 같은_위치면_equals가_true다() {
        Position a = Position.of(2);
        Position b = Position.of(2);
        assertThat(a).isEqualTo(b);
    }
}