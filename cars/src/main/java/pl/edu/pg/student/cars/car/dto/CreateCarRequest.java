package pl.edu.pg.student.cars.car.dto;

import lombok.*;
import pl.edu.pg.student.cars.car.entity.Car;
import pl.edu.pg.student.cars.producer.entity.Producer;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A DTO for the {@link Car} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCarRequest {
    private String name;
    private String producer;
    private int horsePower;

    public static Function<CreateCarRequest, Car> dtoToEntityMapper(
            Supplier<Producer> producerSupplier) {
        return request -> Car.builder()
                .name(request.getName())
                .producer(producerSupplier.get())
                .horsePower(request.getHorsePower())
                .build();
    }
}