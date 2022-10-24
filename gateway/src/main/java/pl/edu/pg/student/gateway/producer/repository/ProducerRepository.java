package pl.edu.pg.student.gateway.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.gateway.producer.entity.Producer;

@org.springframework.stereotype.Repository
public interface ProducerRepository extends JpaRepository<Producer, String> {
    // Random queries here
}
