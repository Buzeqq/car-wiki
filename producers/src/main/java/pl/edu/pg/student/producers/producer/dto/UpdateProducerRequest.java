package pl.edu.pg.student.producers.producer.dto;

import lombok.*;
import pl.edu.pg.student.producers.producer.entity.Producer;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateProducerRequest {
    private int foundationYear;

    public static BiFunction<Producer, UpdateProducerRequest, Producer> dtoToEntityUpdater() {
        return (producer, request) -> {
            producer.setFoundationYear(request.getFoundationYear());
            return producer;
        };
    }
}
