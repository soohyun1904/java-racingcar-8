package racingcar.exception.message;

public enum ErrorMessage {
    // Common
    INVALID_VALUE("[ERROR] 유효하지 않는 값입니다."),
    EMPTY_INPUT("[ERROR] 입력값이 비어있습니다."),
    INVALID_NULL("[ERROR] null 값은 허용되지 않습니다."),

    // Car Name
    EMPTY_CAR_NAMES("[ERROR] 자동차 이름을 입력해 주세요. 최소 1대 이상의 자동차가 필요합니다."),
    INVALID_CAR_NAME_LENGTH("[ERROR] 자동차 이름은 5글자 이하만 가능합니다."),
    DUPLICATE_CAR_NAME("[ERROR] 자동차 이름은 중복될 수 없습니다."),

    // Position
    INVALID_POSITION("[ERROR] 위치는 0보다 작을 수 없습니다."),

    // Try Count
    EMPTY_TRY_COUNT("[ERROR] 시도 횟수를 입력해주세요."),
    INVALID_NUMBER_FORMAT("[ERROR] 시도 횟수는 숫자만 입력 가능합니다."),
    INVALID_TRY_COUNT("[ERROR] 시도 횟수는 1 이상이어야 합니다."),

    // Game
    POLICY_COUNT_MISMATCH("[ERROR] 자동차의 수와 이동 정책의 수가 일치하지 않습니다."),
    CARS_EMPTY("[ERROR] 참여한 자동차가 없어 작업을 수행할 수 없습니다."),
    INVALID_ROUND_NUMBER("[ERROR] 요청하신 라운드는 존재하지 않습니다."),

    // Response
    NEGATIVE_POSITION("[ERROR] 위치는 0 이상이어야 합니다."),
    NULL_RESULT_DATA("[ERROR] 결과 데이터는 null일 수 없습니다."),
    EMPTY_NAME("[ERROR] 이름은 비어있을 수 없습니다."),
    NULL_CAR_STATUS_LIST("[ERROR] 자동차 상태 목록은 null일 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
