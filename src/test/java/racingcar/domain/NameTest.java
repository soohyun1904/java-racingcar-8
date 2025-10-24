package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.DomainValidationException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.exception.message.ErrorMessage.INVALID_CAR_NAME_LENGTH;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "pobi",
            "$%^^&",
            "12345",
            "a123",
            "a!q2",
            "아예이오우"
    })
    @DisplayName("5글자 이하의 문자열로 Name을 생성한다.")
    void CreateValidName(String input){
        Name name = new Name(input);
        assertThat(name.getValue()).isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "pobi123",
            "$%^^&!@",
            "123456",
            "a1q123",
            "a!q2자동차",
            "아예이오우1"
    })
    @DisplayName("6글자 이상이면 예외가 발생한다.")
    void throwExceptionWhenTooLong(String input){
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_CAR_NAME_LENGTH.getMessage());
    }
}