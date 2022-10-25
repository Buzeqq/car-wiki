package pl.edu.pg.student.cars.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.student.cars.producer.entity.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, String> {
}
