package com.example.carsystem.models;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;
    private String licensePlate;
    private String model;
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return id.equals(carEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}