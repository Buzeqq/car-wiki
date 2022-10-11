package pl.edu.pg.student.isa_laboratories.car.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.student.isa_laboratories.car.entity.Producer;
import pl.edu.pg.student.isa_laboratories.datastore.DataStore;
import pl.edu.pg.student.isa_laboratories.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class ProducerRepository implements Repository<Producer, String> {

    final private DataStore store;

    @Autowired
    public ProducerRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Producer> find(String id) {
        return store.findProducer(id);
    }

    @Override
    public List<Producer> findAll() {
        return store.findAllProducers();
    }

    @Override
    public void create(Producer entity) {
        store.createProducer(entity);
    }

    @Override
    public void delete(Producer entity) {
        store.deleteProducer(entity);
    }
}
