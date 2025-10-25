package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.Name;
import racingcar.exception.message.ErrorMessage;
import racingcar.exception.InputParsingException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameParserTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "pobi,woni,jun",
            "pobi, woni, jun",
            "pobi,,woni,jun",
            "pobi, , woni ,jun",
            ",,pobi,woni, ,jun,,",
    })
    @DisplayName("다양한 입력 방식을 올바르게 파싱한다.")
    void parseVariousInputFormats(String input){
        List<Name> names = NameParser.splitWithDelimiter(input);
        assertThat(names).extracting(Name::getValue).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("Name 객체에는 쉼표가 포함되지 않는다.")
    void nameDoesNotContainsComma(){
        String input = "pobi,woni,jun";
        List<Name> names = NameParser.splitWithDelimiter(input);
        assertThat(names).allMatch(name -> !name.getValue().contains(","));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", ",,",", ,"," , "
    })
    @DisplayName("파싱 결과가 비어있다면 예외가 발생한다.")
    void throwExceptionWhenInvalidInput(String input){
        assertThatThrownBy(()->NameParser.splitWithDelimiter(input))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(ErrorMessage.EMPTY_CAR_NAMES.getMessage());
    }

}