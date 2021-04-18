package pl.sda.tableperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "director_tableperclass")
public class Director extends Employee {

    private String department;

    public Director(Long id, String firstName, String lastName, String department) {
        super(id, firstName, lastName);
        this.department = department;
    }
}
