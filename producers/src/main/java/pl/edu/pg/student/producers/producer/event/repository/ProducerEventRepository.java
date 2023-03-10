package pl.edu.pg.student.producers.producer.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.student.producers.producer.entity.Producer;
import pl.edu.pg.student.producers.producer.event.dto.CreateProducerRequest;

@Repository
public class ProducerEventRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public ProducerEventRepository(@Value("${car.cars.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Producer producer) {
        restTemplate.delete("/producers/{name}", producer.getName());
    }

    public void create(Producer producer) {
        restTemplate.postForLocation("/producers", CreateProducerRequest.entityToDtoMapper().apply(producer));
    }
}
