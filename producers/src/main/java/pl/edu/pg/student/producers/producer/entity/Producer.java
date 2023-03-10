package pl.edu.pg.student.producers.producer.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@Entity
@Table(name = "producers")
public class Producer {
    @Id
    private String name;

    private int foundationYear;

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
