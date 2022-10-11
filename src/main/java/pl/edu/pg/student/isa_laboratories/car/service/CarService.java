package pl.edu.pg.student.isa_laboratories.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return repository.find(id);
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    public void create(Car car) {
        repository.create(car);
    }

    public void delete(Long id) {
        repository.delete(repository.find(id).orElseThrow());
    }

}
