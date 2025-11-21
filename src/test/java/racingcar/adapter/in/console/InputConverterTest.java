package racingcar.adapter.in.console;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.exception.common.InvalidInputException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.exception.message.ErrorMessage.*;

class InputConverterTest {
    private final InputConverter converter = new InputConverter();

    @Test
    @DisplayName("자동차 이름을 쉼표로 구분하여 파싱할 수 있다")
    void 자동차_이름_정상_파싱() {
        List<String> result = converter.toCarNames("pobi,java,woni");
        assertThat(result).containsExactly("pobi", "java", "woni");
    }

    @Test
    @DisplayName("자동차 이름 입력이 비어있으면 예외가 발생한다")
    void 자동차_이름_비어있으면_예외() {
        assertThatThrownBy(() -> converter.toCarNames(" "))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(EMPTY_CAR_NAMES.getMessage());
    }

    @Test
    @DisplayName("빈 이름만 입력되면 예외가 발생한다 (예: ', ,')")
    void 자동차_이름_모두_빈칸이면_예외() {
        assertThatThrownBy(() -> converter.toCarNames(" , , "))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(EMPTY_CAR_NAMES.getMessage());
    }

    @Test
    @DisplayName("자동차 이름에 공백이 포함되어도 trim되어 정상 파싱된다")
    void 자동차_이름_trim_정상작동() {
        List<String> result = converter.toCarNames("  pobi ,  java  ");
        assertThat(result).containsExactly("pobi", "java");
    }

    @Test
    @DisplayName("시도 횟수를 정상적으로 파싱할 수 있다")
    void 시도횟수_정상_파싱() {
        int count = converter.toTryCount("5");
        assertThat(count).isEqualTo(5);
    }

    @Test
    @DisplayName("시도 횟수가 비어있으면 예외가 발생한다")
    void 시도횟수_비어있으면_예외() {
        assertThatThrownBy(() -> converter.toTryCount(" "))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(EMPTY_TRY_COUNT.getMessage());
    }

    @Test
    @DisplayName("시도 횟수가 숫자가 아니면 예외가 발생한다")
    void 시도횟수_숫자아니면_예외() {
        assertThatThrownBy(() -> converter.toTryCount("abc"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(INVALID_NUMBER_FORMAT.getMessage());
    }
}