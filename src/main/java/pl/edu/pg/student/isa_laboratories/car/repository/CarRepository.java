package pl.edu.pg.student.isa_laboratories.car.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.student.isa_laboratories.car.entity.Car;
import pl.edu.pg.student.isa_laboratories.datastore.DataStore;

import java.util.List;
import java.util.Optional;
import pl.edu.pg.student.isa_laboratories.repository.Repository;

@org.springframework.stereotype.Repository
public class CarRepository implements Repository<Car, Long> {
    private final DataStore store;

    @Autowired
    public CarRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Car> find(Long id) {
        return store.findCar(id);
    }

    @Override
    public List<Car> findAll() {
        return store.findAllCars();
    }

    @Override
    public void create(Car entity) {
        store.createCar(entity);
    }

    @Override
    public void delete(Car entity) {
        store.deleteCar(entity.getId());
    }
}
