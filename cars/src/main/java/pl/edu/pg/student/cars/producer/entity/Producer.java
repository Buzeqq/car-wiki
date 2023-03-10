package pl.edu.pg.student.cars.producer.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import pl.edu.pg.student.cars.car.entity.Car;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@Entity
@Table(name = "producers")
public class Producer implements Serializable {
    @Id
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "producer")
    @ToString.Exclude
    private List<Car> producedCars;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Producer producer = (Producer) o;
        return name != null && Objects.equals(name, producer.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}