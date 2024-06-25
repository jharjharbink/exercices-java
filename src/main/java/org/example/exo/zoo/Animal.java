package org.example.exo.zoo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int age;
    private String alimentationRegime;
    private String arrivalDate;

    public Animal(String name, int age, String alimentationRegime, String arrivalDate) {
        this.name = name;
        this.age = age;
        this.alimentationRegime = alimentationRegime;
        this.arrivalDate = arrivalDate;
    }
}
