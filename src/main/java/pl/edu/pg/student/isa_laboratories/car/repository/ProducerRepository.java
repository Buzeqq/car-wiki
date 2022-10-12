package pl.edu.pg.student.isa_laboratories.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.isa_laboratories.car.entity.Producer;

@org.springframework.stereotype.Repository
public interface ProducerRepository extends JpaRepository<Producer, String> {
    // Random queries here
}
