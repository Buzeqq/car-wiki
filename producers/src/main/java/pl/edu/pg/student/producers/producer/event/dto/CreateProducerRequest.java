package pl.edu.pg.student.producers.producer.event.dto;

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

    public static Function<Producer, CreateProducerRequest> entityToDtoMapper() {
        return user -> CreateProducerRequest.builder()
                .name(user.getName())
                .build();
    }
}
