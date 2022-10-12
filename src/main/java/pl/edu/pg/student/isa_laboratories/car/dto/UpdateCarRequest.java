package pl.edu.pg.student.isa_laboratories.car.dto;

import lombok.*;
import pl.edu.pg.student.isa_laboratories.car.entity.Car;

import java.util.function.BiFunction;

/**
 * A DTO for the {@link pl.edu.pg.student.isa_laboratories.car.entity.Car} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateCarRequest {
    private String name;

    private int horsePower;

    public static BiFunction<Car, UpdateCarRequest, Car> dtoToEntityUpdater() {
        return (car, request) -> {
            car.setName(request.getName());
            car.setHorsePower(request.getHorsePower());
            return car;
        };
    }
}