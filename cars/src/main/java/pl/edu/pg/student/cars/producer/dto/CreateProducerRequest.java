package pl.edu.pg.student.cars.producer.dto;

import lombok.*;
import pl.edu.pg.student.cars.producer.entity.Producer;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateProducerRequest {
    private String name;

    public static Function<CreateProducerRequest, Producer> dtoToEntityMapper() {
        return request -> Producer.builder()
                .name(request.getName())
                .build();
    }
}
