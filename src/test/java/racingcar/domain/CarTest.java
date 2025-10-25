package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @Test
    @DisplayName("전진 조건의 경계값(4)에서 자동차가 이동한다")
    void moveAtBoundaryValue(){
        Car car = new Car(new Name("pobi"));
        car.move(4);
        assertThat(car.getPositionValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("전진 조건의 경계값(3)에서 자동차가 이동하지 않는다")
    void otMoveAtBoundaryValue(){
        Car car = new Car(new Name("pobi"));
        car.move(3);
        assertThat(car.getPositionValue()).isEqualTo(0);
    }
}