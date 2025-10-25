package racingcar.dto;

import racingcar.domain.Car;

public record CarStatusDto(String name, int position) {
    private static final String POSITION_SYMBOL = "-";
    private static final String NAME_POSITION_DELIMITER = " : ";

    public static CarStatusDto from(Car car) {
        return new CarStatusDto(car.getNameValue(), car.getPositionValue());
    }

    public String toDisplayString(){
        return name + NAME_POSITION_DELIMITER + POSITION_SYMBOL.repeat(position);
    }
}
