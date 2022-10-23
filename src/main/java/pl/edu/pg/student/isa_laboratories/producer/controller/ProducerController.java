package pl.edu.pg.student.isa_laboratories.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.student.isa_laboratories.producer.entity.Producer;
import pl.edu.pg.student.isa_laboratories.producer.service.ProducerService;
import pl.edu.pg.student.isa_laboratories.producer.dto.CreateProducerRequest;
import pl.edu.pg.student.isa_laboratories.producer.dto.GetProducerResponse;
import pl.edu.pg.student.isa_laboratories.producer.dto.GetProducersResponse;
import pl.edu.pg.student.isa_laboratories.producer.dto.UpdateProducerRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/producers")
public class ProducerController {
    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<GetProducersResponse> getProducers() {
        List<Producer> all = producerService.findAll();
        Function<Collection<Producer>, GetProducersResponse> mapper = GetProducersResponse.entityToDtoMapper();
        GetProducersResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetProducerResponse> getProducer(@PathVariable("name") String name) {
        return  producerService.find(name)
                .map(value -> ResponseEntity.ok(GetProducerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createProducer(@RequestBody CreateProducerRequest request, UriComponentsBuilder builder) {
        Producer producer = CreateProducerRequest.dtoToEntityMapper().apply(request);
        producer = producerService.create(producer);
        return ResponseEntity.created(builder.pathSegment("api", "producers", "{name}")
                .buildAndExpand(producer.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteCar(@PathVariable("name") String name) {
        Optional<Producer> producer = producerService.find(name);
        if (producer.isPresent()) {
            producerService.delete(producer.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateProducer(@RequestBody UpdateProducerRequest request, @PathVariable("name") String name) {
        Optional<Producer> producer = producerService.find(name);
        if (producer.isPresent()) {
            UpdateProducerRequest.dtoToEntityUpdater().apply(producer.get(), request);
            producerService.update(producer.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
