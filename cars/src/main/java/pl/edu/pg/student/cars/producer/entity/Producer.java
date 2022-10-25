package pl.edu.pg.student.cars.producer.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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