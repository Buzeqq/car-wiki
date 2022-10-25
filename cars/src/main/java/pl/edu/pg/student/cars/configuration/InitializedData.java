package pl.edu.pg.student.cars.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.cars.car.entity.Car;
import pl.edu.pg.student.cars.car.service.CarService;
import pl.edu.pg.student.cars.producer.entity.Producer;
import pl.edu.pg.student.cars.producer.service.ProducerService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final CarService carService;

    private final ProducerService producerService;

    @Autowired
    public InitializedData(CarService carService, ProducerService producerService) {
        this.carService = carService;
        this.producerService = producerService;
    }

    @PostConstruct
    private synchronized void init() {
        Producer mazda = Producer.builder().name("Mazda").build();
        Producer honda = Producer.builder().name("Honda").build();
        Producer mitsubishi = Producer.builder().name("Mitsubishi").build();
        Producer toyota = Producer.builder().name("Toyota").build();

        producerService.create(mazda);
        producerService.create(honda);
        producerService.create(mitsubishi);
        producerService.create(toyota);

        Car mx5 = Car.builder()
                .name("MX-5")
                .horsePower(116)
                .producer(mazda)
                .build();

        Car cx3 = Car.builder()
                .name("CX-3")
                .horsePower(134)
                .producer(mazda)
                .build();

        Car civic = Car.builder()
                .name("Civic Type R TC")
                .horsePower(330)
                .producer(honda)
                .build();

        Car crV = Car.builder()
                .name("CR-V")
                .horsePower(160)
                .producer(honda)
                .build();

        Car lancer = Car.builder()
                .name("Lancer")
                .horsePower(250)
                .producer(mitsubishi)
                .build();

        Car l200Stark = Car.builder()
                .name("L200 Stark")
                .horsePower(185)
                .producer(mitsubishi)
                .build();

        Car supra = Car.builder()
                .name("Supra")
                .horsePower(258)
                .producer(toyota)
                .build();

        Car ae86 = Car.builder()
                .name("AE86")
                .horsePower(123)
                .producer(toyota)
                .build();

        carService.create(mx5);
        carService.create(cx3);
        carService.create(civic);
        carService.create(crV);
        carService.create(lancer);
        carService.create(l200Stark);
        carService.create(supra);
        carService.create(ae86);
    }
}
