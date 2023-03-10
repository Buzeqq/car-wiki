package pl.edu.pg.student.producers.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.student.producers.producer.entity.Producer;
import pl.edu.pg.student.producers.producer.event.repository.ProducerEventRepository;
import pl.edu.pg.student.producers.producer.repository.ProducerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {

    private final ProducerRepository repository;

    private final ProducerEventRepository eventRepository;

    @Autowired
    public ProducerService(ProducerRepository repository, ProducerEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Producer> find(String name) {
        return repository.findById(name);
    }

    @Transactional
    public void create(Producer producer) {
        repository.save(producer);
        eventRepository.create(producer);
    }

    public List<Producer> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(Producer producer) {
        eventRepository.delete(producer);
        repository.delete(producer);
    }

    @Transactional
    public void update(Producer producer) {
        // eventRepository.update(producer);
        repository.save(producer);
    }
}
