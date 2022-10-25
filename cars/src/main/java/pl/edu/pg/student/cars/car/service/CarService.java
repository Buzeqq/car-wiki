package pl.edu.pg.student.cars.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.student.cars.car.entity.Car;
import pl.edu.pg.student.cars.car.repository.CarRepository;
import pl.edu.pg.student.cars.producer.entity.Producer;
import pl.edu.pg.student.cars.producer.repository.ProducerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    private final ProducerRepository producerRepository;

    @Autowired
    public CarService(CarRepository carRepository, ProducerRepository producerRepository) {
        this.carRepository = carRepository;
        this.producerRepository = producerRepository;
    }

    public Optional<Car> find(Long id) {
        return carRepository.findById(id);
    }

    public Optional<Car> find(String name, Long id) {
        Optional<Producer> producer = producerRepository.findById(name);
        if (producer.isPresent()) {
            return carRepository.findByProducerAndId(producer.get(), id);
        } else {
            return Optional.empty();
        }
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findAll(Producer producer) {
        return carRepository.findAllByProducer(producer);
    }

    @Transactional
    public Car create(Car car) {
        return carRepository.save(car);
    }

    @Transactional
    public void update(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
