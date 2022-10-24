package pl.edu.pg.student.gateway.car.dto;

import lombok.*;
import pl.edu.pg.student.gateway.car.entity.Car;
import pl.edu.pg.student.gateway.producer.entity.Producer;

import java.util.function.Function;

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
            Function<String, Producer> producerFunction) {
        return request -> Car.builder()
                .name(request.getName())
                .producer(producerFunction.apply(request.getProducer()))
                .horsePower(request.getHorsePower())
                .build();
    }
}