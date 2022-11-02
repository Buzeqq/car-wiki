package pl.edu.pg.student.isa_laboratories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.isa_laboratories.car.entity.Car;
import pl.edu.pg.student.isa_laboratories.producer.entity.Producer;
import pl.edu.pg.student.isa_laboratories.car.service.CarService;
import pl.edu.pg.student.isa_laboratories.producer.service.ProducerService;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {


    private final CarService carService;
    private final ProducerService producerService;

    @Autowired
    public CommandLine(CarService carService, ProducerService producerService) {
        this.carService = carService;
        this.producerService = producerService;
    }

    private void printAvailableCommands() {
        System.out.println("Available commands:");
        System.out.println("\tList producers");
        System.out.println("\tList cars");
        System.out.println("\tCreate car");
        System.out.println("\tDelete car");
        System.out.println("\tStop");
    }

    private void createCar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adding new car");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.println("HP: ");
        int horsePower = Integer.parseInt(scanner.nextLine());

        System.out.println("Available producers:");
        printAllProducers();
        System.out.println("Choose from existing or create new one");
        System.out.print("Producer: ");
        String producerName = scanner.nextLine();

        Producer producer = producerService.find(producerName)
                .orElseGet(() -> createProducer(producerName));

        Car newCar = Car.builder()
                .name(name)
                .producer(producer)
                .horsePower(horsePower)
                .build();

        carService.create(newCar);
        System.out.printf("Successfully added: %s\n", newCar);
    }

    private void deleteCar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose id of element you want to delete");
        printAllCarsById();
        System.out.print("Id: ");
        long id = Long.parseLong(scanner.nextLine());

        carService.find(id).ifPresentOrElse(
                car -> {
                    carService.delete(car.getId());
                    System.out.printf("Successfully deleted %s\n", car);
                },
                () -> System.out.printf("Car not found with id: %d\n", id)
        );
    }

    private Producer createProducer(String producerName) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adding new producer");
        System.out.printf("Name: %s\n", producerName);
        System.out.print("Foundation year: ");
        int foundationYear = Integer.parseInt(scanner.nextLine());

        Producer newProducer = Producer.builder()
                .name(producerName)
                .foundationYear(foundationYear)
                .build();

        producerService.create(newProducer);
        System.out.printf("Successfully created: %s\n", newProducer);
        return newProducer;
    }

    private void printAllProducers() {
        producerService.findAll().forEach(System.out::println);
    }

    private void printAllCars() {
        carService.findAll().forEach(System.out::println);
    }

    private void printAllCarsById() {
        List<Car> cars = carService.findAll();
        cars.sort((Car c1, Car c2) -> (int) (c1.getId() - c2.getId()));
        cars.forEach(System.out::println);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        printAvailableCommands();
        //carService.update();
        while(true) {
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("Stop")) {
                break;
            } else if (command.equalsIgnoreCase("List producers")) {
                printAllProducers();
            } else if (command.equalsIgnoreCase("List cars")) {
                printAllCars();
            } else if (command.equalsIgnoreCase("Create car")) {
                createCar();
            } else if (command.equalsIgnoreCase("Delete car")) {
                deleteCar();
            } else {
                System.out.println("Unknown command.");
                printAvailableCommands();
            }
        }
    }
}
