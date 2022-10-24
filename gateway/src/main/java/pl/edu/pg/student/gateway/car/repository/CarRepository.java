package pl.edu.pg.student.gateway.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.gateway.car.entity.Car;

@org.springframework.stereotype.Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Random queries here
}
