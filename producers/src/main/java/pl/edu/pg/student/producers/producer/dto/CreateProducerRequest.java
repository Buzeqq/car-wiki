package pl.edu.pg.student.producers.producer.dto;

import lombok.*;
import pl.edu.pg.student.producers.producer.entity.Producer;

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
    private int foundationYear;

    public static Function<CreateProducerRequest, Producer> entityToDtoMapper() {
        return request -> Producer.builder()
                .name(request.getName())
                .foundationYear(request.getFoundationYear())
                .build();
    }
}
