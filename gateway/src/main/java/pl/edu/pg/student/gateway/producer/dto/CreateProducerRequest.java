package pl.edu.pg.student.gateway.producer.dto;

import lombok.*;
import pl.edu.pg.student.gateway.producer.entity.Producer;

import java.util.List;
import java.util.function.Function;

/**
 * A DTO for the {@link Producer} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateProducerRequest {
    private String name;

    private int foundationYear;

    public static Function<CreateProducerRequest, Producer> dtoToEntityMapper() {
        return request -> Producer.builder()
                .name(request.getName())
                .foundationYear(request.getFoundationYear())
                .producedCars(List.of())
                .build();
    }
}