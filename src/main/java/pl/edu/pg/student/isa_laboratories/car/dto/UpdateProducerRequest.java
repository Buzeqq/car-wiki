package pl.edu.pg.student.isa_laboratories.car.dto;

import lombok.*;
import pl.edu.pg.student.isa_laboratories.car.entity.Producer;

import java.util.function.BiFunction;

/**
 * A DTO for the {@link pl.edu.pg.student.isa_laboratories.car.entity.Producer} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateProducerRequest {
    private String name;

    private int foundationYear;

    public static BiFunction<Producer, UpdateProducerRequest, Producer> dtoToEntityUpdater() {
        return (producer, request) -> {
            producer.setName(request.getName());
            producer.setFoundationYear(request.getFoundationYear());
            return producer;
        };
    }
}