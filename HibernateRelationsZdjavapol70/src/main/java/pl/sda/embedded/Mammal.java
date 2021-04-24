package pl.sda.embedded;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mammal {

    @Id
    private Integer id;

    @Embedded
    private Animal animal;
}
