package pl.edu.pg.student.isa_laboratories.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.student.isa_laboratories.car.dto.*;
import pl.edu.pg.student.isa_laboratories.car.entity.Car;
import pl.edu.pg.student.isa_laboratories.car.service.CarService;
import pl.edu.pg.student.isa_laboratories.producer.service.ProducerService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/cars")
public class CarController {
    private final CarService carService;

    private final ProducerService producerService;

    @Autowired
    public CarController(CarService carService, ProducerService producerService) {
        this.carService = carService;
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars() {
        List<Car> all = carService.findAll();
        Function<Collection<Car>, GetCarsResponse> mapper = GetCarsResponse.entityToDtoMapper();
        GetCarsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("id") long id) {
        return  carService.find(id)
                .map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CreateCarRequest request, UriComponentsBuilder builder) {
        Car car = CreateCarRequest
                .dtoToEntityMapper(name -> producerService.find(name).orElseThrow())
                .apply(request);
        car = carService.create(car);
        return ResponseEntity.created(builder.pathSegment("api", "cars", "{id}")
                .buildAndExpand(car.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") long id) {
        Optional<Car> car = carService.find(id);
        if (car.isPresent()) {
            carService.delete(car.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<Void> updateCar(@RequestBody UpdateCarRequest request, @PathVariable("id") long id) {
        Optional<Car> car = carService.find(id);
        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
