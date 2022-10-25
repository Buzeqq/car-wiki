package pl.edu.pg.student.cars.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.student.cars.car.dto.CreateCarRequest;
import pl.edu.pg.student.cars.car.dto.GetCarResponse;
import pl.edu.pg.student.cars.car.dto.GetCarsResponse;
import pl.edu.pg.student.cars.car.dto.UpdateCarRequest;
import pl.edu.pg.student.cars.car.entity.Car;
import pl.edu.pg.student.cars.car.service.CarService;
import pl.edu.pg.student.cars.producer.entity.Producer;
import pl.edu.pg.student.cars.producer.service.ProducerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/producers/{name}/cars")
public class ProducerCarController {
    private final CarService carService;
    private final ProducerService producerService;

    @Autowired
    public ProducerCarController(CarService carService, ProducerService producerService) {
        this.carService = carService;
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars(@PathVariable("name") String name) {
        Optional<Producer> producer = producerService.find(name);

        if (producer.isPresent()) {
            List<Car> cars = carService.findAll(producer.get());
            return ResponseEntity.ok(GetCarsResponse.entityToDtoMapper().apply(cars));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("name") String name,
                                                 @PathVariable("id") long id) {
        return carService.find(name, id)
                .map(car -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(car)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@PathVariable("name") String name,
                                          @RequestBody CreateCarRequest request,
                                          UriComponentsBuilder builder) {
        Optional<Producer> producer = producerService.find(name);

        if (producer.isPresent()) {
            Car car = CreateCarRequest
                    .dtoToEntityMapper(producer::get)
                    .apply(request);

            carService.create(car);
            return ResponseEntity.created(builder.pathSegment("api", "producers", "{name}", "cars", "{id}")
                    .buildAndExpand(producer.get().getName(), car.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("name") String name,
                                          @RequestBody UpdateCarRequest request,
                                          @PathVariable("id") long id) {
        Optional<Car> car = carService.find(name, id);

        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
