package racingcar.adapter.in.console;

import racingcar.exception.common.InvalidInputException;
import racingcar.exception.message.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import static racingcar.exception.message.ErrorMessage.*;

public class InputConverter {
    private static final String CAR_NAME_DELIMITER = ",";

    public List<String> toCarNames(String input) {
        validateNotEmpty(input, EMPTY_CAR_NAMES);

        List<String> names = Arrays.stream(input.split(CAR_NAME_DELIMITER))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .toList();

        validateNotEmpty(names);
        return names;
    }

    public int toTryCount(String input) {
        validateNotEmpty(input, EMPTY_TRY_COUNT);

        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INVALID_NUMBER_FORMAT);
        }
    }

    private void validateNotEmpty(String input, ErrorMessage errorMessage) {
        if (input == null || input.isBlank()) {
            throw new InvalidInputException(errorMessage);
        }
    }

    private void validateNotEmpty(List<String> names) {
        if (names.isEmpty()) {
            throw new InvalidInputException(EMPTY_CAR_NAMES);
        }
    }
}
