package pl.edu.pg.student.isa_laboratories.car.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

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
public class GetCarsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Car {
        private Long id;

        private String name;
    }

    @Singular
    private List<Car> cars;

    public static Function<Collection<pl.edu.pg.student.isa_laboratories.car.entity.Car>, GetCarsResponse> entityToDtoMapper() {
        return cars -> {
            GetCarsResponseBuilder response = GetCarsResponse.builder();
            cars.stream()
                    .map(car -> Car.builder()
                            .id(car.getId())
                            .name(car.getName())
                            .build())
                    .forEach(response::car);
            return response.build();
        };
    }
}