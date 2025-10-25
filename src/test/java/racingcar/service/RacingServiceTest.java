package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.CarStatusDto;
import racingcar.exception.DomainValidationException;
import racingcar.exception.InputParsingException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.exception.message.ErrorMessage.DUPLICATE_CAR_NAME;
import static racingcar.exception.message.ErrorMessage.EMPTY_CAR_NAMES;

public class RacingServiceTest {
    private RacingService racingService;

    @BeforeEach
    void set(){
        racingService = new RacingService(() -> 4);
    }

    @Test
    @DisplayName("자동차 이름을 입력받아 Cars 객체를 생성한다.")
    void createCars(){
        Cars cars = racingService.createCars("pobi,crong,ady");
        assertThat(cars.getCars())
                .extracting(Car::getNameValue)
                .containsExactly("pobi", "crong", "ady");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", ",,",", ,"," , "
    })
    @DisplayName("파싱 결과가 비어있다면 예외가 발생한다.")
    void throwExceptionWhenInvalidInput(String input){
        assertThatThrownBy(() -> racingService.createCars(input))
                .isInstanceOf(InputParsingException.class)
                .hasMessage(EMPTY_CAR_NAMES.getMessage());
    }

    @Test
    @DisplayName("중복된 자동차 이름으로 생성 시 예외가 발생한다.")
    void throwExceptionWhenDuplicateCarNames(){
        assertThatThrownBy(() -> racingService.createCars("pobi,crong,pobi"))
                .isInstanceOf(DomainValidationException.class)
                .hasMessage(DUPLICATE_CAR_NAME.getMessage());
    }

    @Test
    @DisplayName("한 라운드를 진행하면 모든 자동차가 랜덤하게 이동한다")
    void playRound() {
        Cars cars = racingService.createCars("pobi,crong,ady");
        racingService.playRound(cars);
        List<CarStatusDto> statuses = racingService.getCarStatuses(cars);
        assertThat(statuses).allMatch(status -> status.position() == 1);
    }

    @Test
    @DisplayName("랜덤 값이 3 이하면 자동차가 이동하지 않는다.")
    void notMoveWhenRandomNumberIsLessThanFour(){
        racingService = new RacingService(() -> 3);
        Cars cars = racingService.createCars("pobi,crong,ady");
        racingService.playRound(cars);
        List<CarStatusDto> statuses = racingService.getCarStatuses(cars);
        assertThat(statuses).allMatch(status -> status.position() == 0);
    }

    @Test
    @DisplayName("우승자가 여러 명인 경우 모든 우승자 이름을 반환한다")
    void findMultipleWinners() {
        Cars cars = racingService.createCars("pobi,crong,ady");
        racingService.playRound(cars);
        List<String> winners = racingService.findWinners(cars);
        assertThat(winners).containsExactly("pobi", "crong", "ady");
    }
}
