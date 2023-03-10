package pl.edu.pg.student.producers.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.producers.producer.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, String> {
}
