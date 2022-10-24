package pl.edu.pg.student.gateway.car.dto;

import lombok.*;
import pl.edu.pg.student.gateway.car.entity.Car;

import java.util.function.Function;

/**
 * A DTO for the {@link pl.edu.pg.student.gateway.car.entity.Car} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCarResponse {
    private Long id;
    private String name;
    private String producer;
    private int horsePower;

    public static Function<Car, GetCarResponse> entityToDtoMapper() {
        return car -> GetCarResponse.builder()
                .id(car.getId())
                .name(car.getName())
                .producer(car.getProducer().getName())
                .horsePower(car.getHorsePower())
                .build();
    }
}