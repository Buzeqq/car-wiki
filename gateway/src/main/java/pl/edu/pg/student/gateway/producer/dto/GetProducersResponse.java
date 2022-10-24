package pl.edu.pg.student.gateway.producer.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * A DTO for the {@link pl.edu.pg.student.gateway.producer.entity.Producer} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetProducersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Producer {
        private String name;
    }

    @Singular
    private List<Producer> producers;

    public static Function<Collection<pl.edu.pg.student.gateway.producer.entity.Producer>, GetProducersResponse> entityToDtoMapper() {
        return producers -> {
            GetProducersResponseBuilder response = GetProducersResponse.builder();
            producers.stream()
                    .map(producer -> Producer.builder()
                            .name(producer.getName())
                            .build())
                    .forEach(response::producer);
            return response.build();
        };
    }
}