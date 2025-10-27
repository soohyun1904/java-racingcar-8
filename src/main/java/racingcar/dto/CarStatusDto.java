package racingcar.dto;

import racingcar.domain.Car;

public record CarStatusDto(String name, int position) {
    public static CarStatusDto from(Car car) {
        return new CarStatusDto(car.getNameValue(), car.getPositionValue());
    }
}
