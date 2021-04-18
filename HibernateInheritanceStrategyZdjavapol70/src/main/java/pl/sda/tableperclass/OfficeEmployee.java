package pl.sda.tableperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "office_emp_tableperclass")
public class OfficeEmployee extends Employee {

    private String skills;

    public OfficeEmployee(Long id, String firstName, String lastName, String skills) {
        super(id, firstName, lastName);
        this.skills = skills;
    }
}
