package racingcar.exception.message;

public enum ErrorMessage {
    INVALID_VALUE("[ERROR] 유효하지 않는 값입니다."),
    EMPTY_CAR_NAMES("[ERROR] 자동차 이름을 입력해 주세요. 최소 1대 이상의 자동차가 필요합니다."),
    INVALID_CAR_NAME_LENGTH("[ERROR] 자동차 이름은 5글자 이하만 가능합니다."),
    DUPLICATE_CAR_NAME("[ERROR] 자동차 이름은 중복될 수 없습니다."),

    EMPTY_TRY_COUNT("[ERROR] 시도 횟수를 입력해주세요."),
    INVALID_NUMBER_FORMAT("[ERROR] 시도 횟수는 숫자만 입력 가능합니다."),
    INVALID_TRY_COUNT("[ERROR] 시도 횟수는 1 이상이어야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
