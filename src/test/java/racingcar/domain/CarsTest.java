package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.exception.DomainValidationException;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static racingcar.exception.message.ErrorMessage.DUPLICATE_CAR_NAME;

class CarsTest {

    @Test
    @DisplayName("자동차 목록을 생성한다")
    void createCars() {
        List<Name> names = List.of(new Name("pobi"), new Name("crong"));

        Cars cars = new Cars(names);

        assertThat(cars.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("중복된 이름으로 자동차를 생성하면 예외가 발생한다")
    void throwExceptionWhenDuplicateNames() {
        List<Name> names = List.of(new Name("pobi"), new Name("pobi"));

        assertThatThrownBy(() -> new Cars(names))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(DUPLICATE_CAR_NAME.getMessage());
    }
}