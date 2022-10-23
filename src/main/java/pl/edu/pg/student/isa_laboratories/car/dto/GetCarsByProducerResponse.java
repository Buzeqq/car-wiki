package pl.edu.pg.student.isa_laboratories.car.dto;

import lombok.*;
import pl.edu.pg.student.isa_laboratories.producer.entity.Producer;

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
public class GetCarsByProducerResponse {
    @Singular
    List<GetCarsResponse.Car> cars;

    public static Function<Producer, GetCarsByProducerResponse> entityToDtoMapper() {
        return producer -> {
            GetCarsByProducerResponseBuilder response = GetCarsByProducerResponse.builder();
            producer.getProducedCars().stream()
                    .map(car -> GetCarsResponse.Car.builder()
                            .id(car.getId())
                            .name(car.getName())
                            .build())
                    .forEach(response::car);
            return response.build();
        };
    }
}