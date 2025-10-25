package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("move 호출 시 위치가 1 증가한다.")
    void move(){
        Position position = new Position();
        Position result = position.move();
        assertThat(result.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("move를 여러번 호출하면 위치가 누적된다.")
    void moveMultipleTimes(){
        Position position = new Position();
        position = position.move();
        position = position.move();
        position = position.move();
        assertThat(position.getValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("move 호출 시 기존 Position 객체는 변경되지 않는다.")
    void immutability(){
        Position original = new Position();
        Position moved = original.move();
        assertThat(original).isNotEqualTo(moved);
    }
}