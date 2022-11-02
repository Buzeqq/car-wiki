package pl.edu.pg.student.isa_laboratories.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.isa_laboratories.car.entity.Car;

@org.springframework.stereotype.Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Random queries here
}
