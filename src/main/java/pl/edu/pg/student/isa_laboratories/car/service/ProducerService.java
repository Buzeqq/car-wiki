package pl.edu.pg.student.isa_laboratories.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.student.isa_laboratories.car.entity.Producer;
import pl.edu.pg.student.isa_laboratories.car.repository.ProducerRepository;

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
        return repository.find(name);
    }

    public List<Producer> findAll() {
        return repository.findAll();
    }

    public void create(Producer producer) {
        repository.create(producer);
    }

    public void delete(String name) {
        repository.delete(repository.find(name).orElseThrow());
    }

}
