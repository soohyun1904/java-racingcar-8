package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.dto.GameResultDto;
import racingcar.dto.RoundResultDto;
import racingcar.exception.DomainValidationException;
import racingcar.exception.InputParsingException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.exception.message.ErrorMessage.*;

public class RacingServiceTest {
    private RacingService racingService;
    private String tryCount;

    @BeforeEach
    void set() {
        racingService = new RacingService(() -> 4);
        tryCount = "5";
    }

    @Test
    @DisplayName("게임 실행 후 라운드 결과와 우승자를 반환한다.")
    void runGameReturnGameResult() {
        GameResultDto gameResults = racingService.runGame("pobi, crong, ady", tryCount);
        assertThat(gameResults.getRoundResults()).hasSize(5);
        assertThat(gameResults.getWinnerCarNames()).containsExactly("pobi", "crong", "ady");
    }

    @Test
    @DisplayName("랜덤 값이 4 이상이면 자동차가 이동한다")
    void moveWhenRandomNumberIsGreaterThanOrEqualToFour() {
        GameResultDto gameResult = racingService.runGame("pobi,crong,ady", tryCount);
        List<RoundResultDto> roundResults = gameResult.getRoundResults();
        RoundResultDto roundResult = roundResults.get(4);
        assertThat(roundResult.getCarStatuses()).allMatch(status -> status.position() == 5);
    }

    @Test
    @DisplayName("랜덤 값이 3 이하면 자동차가 이동하지 않는다.")
    void notMoveWhenRandomNumberIsLessThanFour(){
        racingService = new RacingService(() -> 3);
        GameResultDto gameResult = racingService.runGame("pobi,crong,ady", tryCount);
        List<RoundResultDto> roundResults = gameResult.getRoundResults();
        for (RoundResultDto roundResult : roundResults) {
            assertThat(roundResult.getCarStatuses())
                    .allMatch(status -> status.position() == 0);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", ",,", ", ,", " , "
    })
    @DisplayName("파싱 결과가 비어있다면 예외가 발생한다.")
    void throwExceptionWhenInvalidInput(String input) {
        assertThatThrownBy(() -> racingService.runGame(input, tryCount))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(EMPTY_CAR_NAMES.getMessage());
    }

    @Test
    @DisplayName("중복된 자동차 이름으로 생성 시 예외가 발생한다.")
    void throwExceptionWhenDuplicateCarNames(){
        assertThatThrownBy(() -> racingService.runGame("pobi,crong,pobi", tryCount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(DUPLICATE_CAR_NAME.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab","!@","3.5"})
    @DisplayName("시도할 횟수가 유효하지 않은 처리를 받으면 예외처리한다.")
    void throwExceptionWhenInputIsNotInteger(String count){
        assertThatThrownBy(() -> racingService.runGame("pobi,crong,qq", count))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(INVALID_NUMBER_FORMAT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-5","0"})
    @DisplayName("1보다 작은 수를 받으면 예외처리한다.")
    void throwExceptionWhenInputIsLessThanOne(String count){
        assertThatThrownBy(() -> racingService.runGame("pobi,crong,qq", count))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(INVALID_TRY_COUNT.getMessage());
    }

    @Test
    @DisplayName("빈 입력을 받는 경우 예외처리한다.")
    void throwExceptionWhenInputIsEmpty(){
        String count = " ";
        assertThatThrownBy(() -> racingService.runGame("pobi,crong,qq", count))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(EMPTY_TRY_COUNT.getMessage());
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
        assertThatThrownBy(() -> racingService.runGame(input, tryCount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(INVALID_CAR_NAME_LENGTH.getMessage());
    }

    @Test
    @DisplayName("중복된 이름으로 자동차를 생성하면 예외가 발생한다")
    void throwExceptionWhenDuplicateNames() {
        assertThatThrownBy(() -> racingService.runGame("pobi,pobi", tryCount))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(DUPLICATE_CAR_NAME.getMessage());
    }
}

