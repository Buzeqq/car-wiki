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
public class GetProducerResponse {
    private String name;
    private int foundationYear;

    public static Function<Producer, GetProducerResponse> entityToDtoMapper() {
        return producer -> GetProducerResponse.builder()
                .name(producer.getName())
                .foundationYear(producer.getFoundationYear())
                .build();
    }
}
