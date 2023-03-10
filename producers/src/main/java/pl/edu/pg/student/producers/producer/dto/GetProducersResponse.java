package pl.edu.pg.student.producers.producer.dto;

import lombok.*;
import pl.edu.pg.student.producers.producer.entity.Producer;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetProducersResponse {
    @Singular
    private List<String> producers;

    public static Function<Collection<Producer>, GetProducersResponse> entityToDtoMapper() {
        return producers -> {
            GetProducersResponseBuilder response = GetProducersResponse.builder();
            producers.stream()
                    .map(Producer::getName)
                    .forEach(response::producer);
            return response.build();
        };
    }
}