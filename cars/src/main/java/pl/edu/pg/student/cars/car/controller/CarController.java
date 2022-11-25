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

import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarController {

    public final CarService carService;
    public final ProducerService producerService;

    @Autowired
    public CarController(CarService carService, ProducerService producerService) {
        this.carService = carService;
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars() {
        return ResponseEntity
                .ok(GetCarsResponse.entityToDtoMapper().apply(carService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("id") Long id) {
        Optional<Car> car = carService.find(id);
        return car.map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CreateCarRequest request, UriComponentsBuilder builder) {
        Optional<Producer> producer = producerService.find(request.getProducer());

        if (producer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Car car = CreateCarRequest
                .dtoToEntityMapper(producer::get)
                .apply(request);

        carService.create(car);

        return ResponseEntity
                .created(builder
                        .pathSegment("api", "cars", "{id}").buildAndExpand(car.getId()).toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        Optional<Car> car = carService.find(id);

        if (car.isPresent()) {
            carService.delete(car.get().getId());

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCar(@RequestBody UpdateCarRequest request, @PathVariable("id") long id) {
        Optional<Car> car = carService.find(id);

        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
