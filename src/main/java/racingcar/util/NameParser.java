package racingcar.util;

import racingcar.domain.Name;
import java.util.Arrays;
import java.util.List;
import static racingcar.util.InputValidator.validateNamesInput;

public class NameParser{
    private static final String DELIMITER = ",";

    private NameParser() {
    }

    public static List<Name> splitWithDelimiter(String input) {
        List<Name> names = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .map(Name::new)
                .toList();
        validateNamesInput(names);
        return names;
    }
}
