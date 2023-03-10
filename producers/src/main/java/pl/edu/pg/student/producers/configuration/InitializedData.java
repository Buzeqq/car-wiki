package pl.edu.pg.student.producers.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.producers.producer.entity.Producer;
import pl.edu.pg.student.producers.producer.service.ProducerService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final ProducerService producerService;

    @Autowired
    public InitializedData(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostConstruct
    private synchronized void init() {
        Producer mazda = Producer.builder().name("Mazda").foundationYear(1920).build();
        Producer honda = Producer.builder().name("Honda").foundationYear(1948).build();
        Producer mitsubishi = Producer.builder().name("Mitsubishi").foundationYear(1969).build();
        Producer toyota = Producer.builder().name("Toyota").foundationYear(1937).build();

        producerService.create(mazda);
        producerService.create(honda);
        producerService.create(mitsubishi);
        producerService.create(toyota);
    }
}
