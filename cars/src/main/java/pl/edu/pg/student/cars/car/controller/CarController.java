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

import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarController {

    public final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
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
        Car car = CreateCarRequest
                .dtoToEntityMapper(() -> null)
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
