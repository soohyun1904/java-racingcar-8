package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.InputParsingException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.exception.message.ErrorMessage.*;

class IntegerParserTest {

    @Test
    @DisplayName("입력된 문자열을 숫자로 파싱한다.")
    void parseInputString(){
        String input ="12";
        int result = IntegerParser.parse(input);
        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("빈 입력을 받는 경우 예외처리한다.")
    void throwExceptionWhenInputIsEmpty(){
        String input = " ";
        assertThatThrownBy(() -> IntegerParser.parse(input))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(EMPTY_TRY_COUNT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab","!@","3.5"})
    @DisplayName("유효하지 않은 처리를 받으면 예외처리한다.")
    void throwExceptionWhenInputIsNotInteger(String input){
        assertThatThrownBy(() -> IntegerParser.parse(input))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(INVALID_NUMBER_FORMAT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-5","0"})
    @DisplayName("1보다 작은 수를 받으면 예외처리한다.")
    void throwExceptionWhenInputIsLessThanOne(String input){
        assertThatThrownBy(() -> IntegerParser.parse(input))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(INVALID_TRY_COUNT.getMessage());
    }
}