package pl.edu.pg.student.cars.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.student.cars.producer.dto.CreateProducerRequest;
import pl.edu.pg.student.cars.producer.entity.Producer;
import pl.edu.pg.student.cars.producer.service.ProducerService;

import java.util.Optional;

@RestController
@RequestMapping("api/producers")
public class ProducerController {

    private final ProducerService service;

    @Autowired
    public ProducerController(ProducerService service) {
        this.service = service;
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteProducer(@PathVariable("name") String name) {
        Optional<Producer> producer = service.find(name);

        if (producer.isPresent()) {
            service.delete(name);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createProducer(@RequestBody CreateProducerRequest request, UriComponentsBuilder builder) {
        Producer producer = CreateProducerRequest.dtoToEntityMapper().apply(request);

        service.create(producer);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "producers", "{name}")
                        .buildAndExpand(producer.getName()).toUri())
                .build();
    }
}
