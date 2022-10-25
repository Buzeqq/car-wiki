package pl.edu.pg.student.cars.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.cars.car.entity.Car;
import pl.edu.pg.student.cars.producer.entity.Producer;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByProducer(Producer producer);

    Optional<Car> findByProducerAndId(Producer producer, Long id);
}
