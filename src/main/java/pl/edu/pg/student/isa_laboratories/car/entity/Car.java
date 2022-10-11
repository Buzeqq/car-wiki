package pl.edu.pg.student.isa_laboratories.car.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@EqualsAndHashCode()
public class Car implements Serializable {
    private Long id;
    private String name;
    private Producer producer;
    private int horsePower;
}
