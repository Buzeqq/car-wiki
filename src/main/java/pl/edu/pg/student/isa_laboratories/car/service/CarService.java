package pl.edu.pg.student.isa_laboratories.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.student.isa_laboratories.car.entity.Car;
import pl.edu.pg.student.isa_laboratories.car.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public Optional<Car> find(Long id) {
        return repository.findById(id);
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Car create(Car car) {
        return repository.save(car);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(Car car) {
        repository.save(car);
    }
}
