package pl.edu.pg.student.gateway.datastore;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.gateway.serialization.CloningUtility;
import pl.edu.pg.student.gateway.car.entity.Car;
import pl.edu.pg.student.gateway.producer.entity.Producer;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Component
public class DataStore {
    private final Set<Producer> producers = new HashSet<>();

    private final Set<Car> cars = new HashSet<>();

    public synchronized List<Producer> findAllProducers() {
        return new ArrayList<>(producers);
    }

    public synchronized Optional<Producer> findProducer(String name) {
        return producers.stream()
                .filter(producer -> producer.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createProducer(Producer producer) throws IllegalArgumentException {
        findProducer(producer.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The producer name \"%s\" is not unique", producer.getName()));
                },
                () -> producers.add(CloningUtility.clone(producer)));
    }

    public synchronized void deleteProducer(Producer producer) throws IllegalArgumentException {
        findProducer(producer.getName()).ifPresentOrElse(
                producers::remove,
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The producer name \"%s\" not found", producer)
                    );
                }
        );
    }

    public synchronized List<Car> findAllCars() {
        return cars.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Car> findCar(Long id) {
        return cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createCar(Car car) throws IllegalArgumentException {
        car.setId(findAllCars().stream()
                .mapToLong(Car::getId)
                .max().orElse(0) + 1);
        cars.add(CloningUtility.clone(car));
    }

    public synchronized void deleteCar(Long id) throws IllegalArgumentException {
        findCar(id).ifPresentOrElse(
                cars::remove,
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The car with id \"%d\" does not exist", id));
                });
    }
}
