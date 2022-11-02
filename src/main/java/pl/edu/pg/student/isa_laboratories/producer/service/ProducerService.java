package pl.edu.pg.student.isa_laboratories.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.student.isa_laboratories.producer.entity.Producer;
import pl.edu.pg.student.isa_laboratories.producer.repository.ProducerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {

    private final ProducerRepository repository;

    @Autowired
    public ProducerService(ProducerRepository repository) {
        this.repository = repository;
    }


    public Optional<Producer> find(String name) {
        return repository.findById(name);
    }

    public List<Producer> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Producer create(Producer producer) {
        return repository.save(producer);
    }

    @Transactional
    public void delete(String name) {
        repository.deleteById(name);
    }

    @Transactional
    public void update(Producer producer) {
        repository.save(producer);
    }

}
