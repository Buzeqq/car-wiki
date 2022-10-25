package pl.edu.pg.student.producers.producer.event.dto;

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
    private String name;

    public static BiFunction<Producer, pl.edu.pg.student.producers.producer.dto.UpdateProducerRequest, Producer> dtoToEntityUpdater() {
        return (producer, request) -> {
            producer.setName(request.getName());
            return producer;
        };
    }
}
